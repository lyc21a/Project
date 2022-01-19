package com.example.demo;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller 
public class HelloController {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private BorrowerDao borrowerDao;
	@Autowired
	private BookreserveDao bookreserveDao;
	
	@RequestMapping("/")
	public String hello(Model model) {		//index.jsp
		return "index";
	}
	
	@RequestMapping("/login")
	public ModelAndView login(ModelAndView mav) {
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping("/menu")
	public ModelAndView menu(ModelAndView mav,
			@RequestParam(value = "ID", required = false) String id) {
		Member mem = memberDao.selectByEmail(id);
		mav.addObject("name", mem.getName());
		mav.addObject("addr", mem.getAddress());
		mav.addObject("ph", mem.getPhone());
		mav.setViewName("menu");
		return mav;
	}
	
	@RequestMapping("/logincheck")
	public ModelAndView logincheck(ModelAndView mav, 
			@RequestParam(value = "ID", required = false) String id, 
			@RequestParam(value = "PWD", required = false) String pwd) {
		
		Member mem = memberDao.selectByEmail(id);
		if(memberDao.selectByEmail(id) != null) {
			if(mem.getPassword().equals(pwd)) {
				mav.addObject("name", mem.getName());
				mav.addObject("addr", mem.getAddress());
				mav.addObject("ph", mem.getPhone());
				mav.setViewName("menu");
				return mav;
			}			
		}
		String errorID = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
		mav.addObject("errorID",errorID);
		mav.setViewName("index"); //logincheck.jsp
		return mav;
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(ModelAndView mav,
			@RequestParam(value = "ID", required = false) String id) {
		
		memberDao.delete(id);
		bookreserveDao.delete(id);
		mav.setViewName("delete");
		return mav;
	}
	
	@RequestMapping("/revise")
	public ModelAndView revise(ModelAndView mav,
			@RequestParam(value = "ID", required = false) String id) {
		Member mem = memberDao.selectByEmail(id);
		mav.addObject("name", mem.getName());
		mav.addObject("addr", mem.getAddress());
		mav.addObject("ph", mem.getPhone());
		mav.setViewName("revise");
		return mav;
	}
	
	@RequestMapping("/revise_process")
	public ModelAndView revise_process(ModelAndView mav, 
			@RequestParam(value = "ID", required = false) String id,
			@RequestParam(value = "oldPWD", required = false) String oldpwd,
			@RequestParam(value = "newPWD", required = false) String newpwd,
			@RequestParam(value = "NAME", required = false) String name,
			@RequestParam(value = "ADDR", required = false) String addr,
			@RequestParam(value = "PHONE", required = false) int phone){
		try {
			memberDao.changePassword(id, oldpwd, newpwd, name, addr, phone);
			mav.setViewName("menu");
		}catch(MemberNotFoundException e) {
			mav.setViewName("revise");
		}catch(IdPasswordNotMatchingException e) {
			String str = "암호가 올바르지 않습니다.";
			mav.addObject("STR",str);
			mav.setViewName("revise");
		}
		return mav;
	}
	
	@RequestMapping("/signup")
	public ModelAndView signup(ModelAndView mav) {
		mav.setViewName("signup");
		return mav;
	}
	
	@RequestMapping("/signup_process")
	public ModelAndView signup_okay(ModelAndView mav, 
			@RequestParam(value = "ID", required = false) String id,
			@RequestParam(value = "PWD", required = false) String pwd,
			@RequestParam(value = "NAME", required = false) String name,
			@RequestParam(value = "ADDR", required = false) String addr,
			@RequestParam(value = "PHONE", required = false) int phone) {
		
		Member member = new Member(id, pwd, name, new Date(), addr, phone);
		Member mem = memberDao.selectByEmail(id);
		
		if(memberDao.selectByEmail(id) == null) {
			memberDao.insert(member);
		}
		else {
			String message = "이미 사용중인 아이디입니다.";
			mav.addObject("message", message);
			mav.setViewName("signup");
			return mav;
		}
		mav.setViewName("signup_process");
		return mav;
	}
	
	@GetMapping(value="/list")
	public String list(Model model) { 
		List<Member> memberList = memberDao.selectAll();
		model.addAttribute("members", memberList);
		return "list";
	}
	
	@RequestMapping("/bookregist")
	public ModelAndView bookregist(ModelAndView mav) {
		mav.setViewName("bookregist");
		return mav;
	}
	
	@RequestMapping("/bookregist_ok")
	public ModelAndView bookregist_ok(ModelAndView mav, 
			@RequestParam(value = "TITLE", required = false) String title,
			@RequestParam(value = "AUTHOR", required = false) String author,
			@RequestParam(value = "STOCK", required = false) int stock) {
		
		Book book = new Book(title, author, stock);
		Book bk = bookDao.selectByTitle(title);
		
		if(bk == null) {
			bookDao.insert(book);
		}
		else {
			bookDao.back(bk, stock);
			return mav;
		}
		
		mav.setViewName("bookregist_ok");
		return mav;
	}

	@RequestMapping("/bookborrow")
	public ModelAndView bookborrow(ModelAndView mav) {
		mav.setViewName("bookborrow");
		return mav;
	}	
	
	@RequestMapping("/bookborrow_ok")
	public ModelAndView bookborrow_ok(ModelAndView mav,
			@RequestParam(value = "ID", required = false) String id,
			@RequestParam(value = "TITLE", required = false) String title,
			@RequestParam(value = "AUTHOR", required = false) String author) {
		
		Book bk = bookDao.selectByTitle(title);
		Member mem = memberDao.selectByEmail(id);
		Borrower br = borrowerDao.selectByEmail(id, title);
	
		Borrower borrower = new Borrower(id, mem.getName(), title, 1, new Date());
		
		if(bk != null) {
			if(bk.getStock() != 0 && bk.getStock() >= 1 && author.equals(bk.getAuthor())) {
				bookDao.borrow(bk, 1);
				if(br == null) {
					borrowerDao.insert(borrower);
				}
				else if(id.equals(br.getEmail()) && title.equals(br.getTitle())){
					borrowerDao.bookcome(br, 1);
				}
			else {
				String message = "현재 남아있는 재고가 없습니다.";
				mav.addObject("message", message);
				mav.setViewName("bookborrow");
				return mav;
			}
		}
		}
		else {
			String message = "보유하고 있지 않은 책입니다.";
			mav.addObject("message", message);
			mav.setViewName("bookborrow");
			return mav;
		}
		
		mav.setViewName("bookborrow_ok");
		return mav;
	}
	
	@RequestMapping("/bookback")
	public ModelAndView bookback(ModelAndView mav) {
		mav.setViewName("bookback");
		return mav;
	}
	
	@RequestMapping("/bookback_ok")
	public ModelAndView bookback_ok(ModelAndView mav,
			@RequestParam(value = "ID", required = false) String id,
			@RequestParam(value = "TITLE", required = false) String title,
			@RequestParam(value = "AUTHOR", required = false) String author) {
		
		Book bk = bookDao.selectByTitle(title);
		Borrower br = borrowerDao.selectByEmail(id, title);
		
		if(bk != null) {
			if(author.equals(bk.getAuthor()) && id.equals(br.getEmail()) && title.equals(br.getTitle())) {
				bookDao.back(bk, 1);
				if(br.getNumbook() <= 1) {
					borrowerDao.delete(br);
				}
				else if(br.getNumbook() > 1) {
					borrowerDao.bookback(br, 1);
				}
				Bookreserve bkr = bookreserveDao.selectBK(title);		//도서예약정보확인
				if(bkr != null) {			//예약정보가 있다면
					Borrower borrower = new Borrower(bkr.getEmail(), bkr.getName(), title, bkr.getBooknum(), new Date());
					bookDao.borrow(bk, bkr.getBooknum());					//도서정보 재고감소
					borrowerDao.insert(borrower);				//대여기록생성
					bookreserveDao.delete(bkr.getEmail());		//예약정보삭제
				}
			}			
			else {
				String message = "도서관에서 소장중인 책이 아닙니다.";
				mav.addObject("message", message);
				mav.setViewName("bookback");
				return mav;
			}
		}
		else {
			String message = "도서관에서 소장중인 책이 아닙니다.";
			mav.addObject("message", message);
			mav.setViewName("bookback");
			return mav;
		}
		
		mav.setViewName("bookback_ok");
		return mav;
	}
	
	@RequestMapping("/bookreserve")
	public ModelAndView bookreserve(ModelAndView mav) {
		List<Book> bookList = bookDao.zeroAll();
		mav.addObject("books", bookList);
		mav.setViewName("bookreserve");
		return mav;
	}
	
	@RequestMapping("/bookreserve_process")
	public ModelAndView bookreserve_process(ModelAndView mav,
			@RequestParam(value = "ID", required = false) String id,
			@RequestParam(value = "BOOKNAME", required = false) String bookname) {
		
		Book bk = bookDao.selectByTitle(bookname);
		Member mem = memberDao.selectByEmail(id);
		
		Bookreserve bkr = new Bookreserve(id, mem.getName(), bookname, 1);
		bookreserveDao.insert(bkr);
				
		mav.setViewName("bookreserve_process");
		return mav;
	}
}