import os
import sys
import urllib.request
import datetime
import time
import json
import pandas as pd

ServiceKey="EZH3I6Pl1MD97VX4sVu52sdVbQxqhPZ4Y3Y4gvdPZg5A5ZRcfA5QVGtHtz4JcxiGqJtDrFd5HWetUWn4YAWe9w%3D%3D"

#[CODE 1]
def getRequestUrl(url):    
    req = urllib.request.Request(url)    
    try: 
        response = urllib.request.urlopen(req)
        if response.getcode() == 200:
            print ("[%s] Url Request Success" % datetime.datetime.now())
            return response.read().decode('utf-8')
    except Exception as e:
        print(e)
        print("[%s] Error for URL : %s" % (datetime.datetime.now(), url))
        return None


#[CODE 2]
def getApartDealItem(local_cd, yyyymm):    
    service_url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"
    parameters = "?_type=json&serviceKey=" + ServiceKey   #인증키
    parameters += "&LAWD_CD=" + local_cd
    parameters += "&DEAL_YMD=" + yyyymm
    parameters += "&numOfRows=" + '1200'
    
    url = service_url + parameters
    
    retData = getRequestUrl(url)   #[CODE 1]
    
    if (retData == None):
        return None
    else:
        return json.loads(retData)

#[CODE 3]
def getApartDealService(code, start, end):
    jsonResult = []
    result = []
    amountSum = 0
    aa_aveSum = 0

    dataEND = "{0}{1:0>2}".format(str(end), str(12))    #데이터 끝 초기화
    isDataEnd = 0                                       #데이터 끝 확인용 flag 초기화
    
    for year in range(start, end+1):
        for month in range(1, 13):
            if(isDataEnd == 1): break
            yyyymm = "{0}{1:0>2}".format(str(year), str(month))
            
            jsonData = getApartDealItem(code, yyyymm) #[CODE 2]


            if jsonData['response']['body']['items'] == '': 
                isDataEnd = 1               #데이터 끝 flag 설정
                end = "{0}{1:0>2}".format(str(year), str(month-1))
                print("데이터 없음.... \n 제공되는 통계 데이터는 %s년 %s월까지입니다."                          
                          %(str(year), str(month-1)))                    
                break
            
            
            jsonDataLen = jsonData['response']['body']['totalCount']
            for cnt in range (0, jsonDataLen):
                amount = jsonData['response']['body']['items']['item'][cnt]['거래금액']        # 거래 금액
                amount = amount.replace(' ','')
                area = jsonData['response']['body']['items']['item'][cnt]['전용면적']          # 전용면적
                dealyear = jsonData['response']['body']['items']['item'][cnt]['년']            # 년, 계약년
                dealmon = jsonData['response']['body']['items']['item'][cnt]['월']             # 월

                amount1 = amount.replace(',','')
                AA_ave = float(amount1) / float(area)
                
                
                jsonResult.append({'거래금액': amount, '전용면적': area, 'yyyymm': yyyymm})
                result.append([amount, area, yyyymm, round(AA_ave, 2)])
                

    return (jsonResult, result)
    
#[CODE 0]
def main():
    local_cd = input('지역 코드를 입력하세요 : ')
    yyy =int(input('몇 년부터? 입력하세요 : '))
    yyy2 =int(input('몇 년까지?  입력하세요 : '))
    
    jsonData, result = getApartDealService(local_cd, yyy, yyy2)
    
    with open('C:\\Users\82105\Desktop/111.json','w', encoding='utf8') as outfile:
        jsonFile  = json.dumps(jsonData, indent=4, sort_keys=True, ensure_ascii=False)
        outfile.write(jsonFile)

    columns = ["거래금액","전용면적","년", "면적 당 금액"]
    result_df = pd.DataFrame(result, columns = columns)
    result_df.to_csv('./%s_%s_%s_아파트_실거래.csv' %(yyy, yyy2, local_cd), index=False, encoding='cp949')


        
if __name__ == '__main__':
    main()
