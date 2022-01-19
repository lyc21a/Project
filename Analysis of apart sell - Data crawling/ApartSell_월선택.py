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
def getApartDealService(jsonData):
    jsonResult = []
    result = []
    amountSum = 0
    aa_aveSum = 0
    jsonDataLen = jsonData['response']['body']['totalCount']
    for cnt in range (0, jsonDataLen):
        if(jsonData['response']['body']['items']['item'] == ""): break
        amount = jsonData['response']['body']['items']['item'][cnt]['거래금액']        # 거래 금액
        build = jsonData['response']['body']['items']['item'][cnt]['건축년도']         # 건축년도
        apart = jsonData['response']['body']['items']['item'][cnt]['아파트']           # 아파트
        area = jsonData['response']['body']['items']['item'][cnt]['전용면적']          # 전용면적
        dong = jsonData['response']['body']['items']['item'][cnt]['법정동']            # 법정동
        dealyear = jsonData['response']['body']['items']['item'][cnt]['년']            # 년, 계약년
        floor = jsonData['response']['body']['items']['item'][cnt]['층']               # 층
        dealmon = jsonData['response']['body']['items']['item'][cnt]['월']             # 월

        amount = amount.replace(' ','')
        amount1 = amount.replace(',','')

        AA_ave = float(amount1) / float(area)

        amountSum += float(amount1)
        aa_aveSum +=AA_ave
        
        jsonResult.append({"거래금액":amount1, "건축년도":build, "아파트":apart,"전용면적":area, "법정동":dong, "년":dealyear, "층":floor, "월":dealmon, "면적당 금액" : AA_ave})
        result.append([amount, build, apart, area, dong, dealyear, floor, dealmon, AA_ave])

    result.append([round(float(amountSum/jsonDataLen),2), 0, 0, 0, 0, 0, 0, 0, round(aa_aveSum/jsonDataLen, 2)])
    return (jsonResult, result)
    
#[CODE 0]
def main():
    local_cd = input('지역 코드를 입력하세요 : ')
    yyyymm =input('계약연월을 입력하세요 : ')
    jsonData = getApartDealItem(local_cd, yyyymm)


    jsonData, result = getApartDealService(jsonData)



    with open('C:\\Users\82105\Desktop/111.json','w', encoding='utf8') as outfile:
        jsonFile  = json.dumps(jsonData, indent=4, sort_keys=True, ensure_ascii=False)
        outfile.write(jsonFile)

    columns = ["거래금액", "건축년도", "아파트", "전용면적", "법정동", "년", "층", "월" ,"면적당 금액"]
    result_df = pd.DataFrame(result, columns = columns)
    result_df.to_csv('./%s_아파트_실거래.csv' %(yyyymm), index=False, encoding='cp949')


        
if __name__ == '__main__':
    main()
