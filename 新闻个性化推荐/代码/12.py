# -*- coding: utf-8 -*-
"""
Created on Sun Oct 12 10:20:35 2014

@author: zhan
"""
import  csv
import datetime
# this is the 8th step of the code ,we divide the data into 31 parts by the scan time to 
#solve the left user

# d sorted by value
def getTimebyString(publishtime):
    year = int (publishtime[0:4])
    mon = int (publishtime[6:8])
    day = int (publishtime[10:12])
    d = datetime.datetime(year,mon,day)
    return d
def sort_by_value(d): 
    items=d.items() 
    backitems=[[v[1],v[0]] for v in items] 
    backitems.sort() 
    return [ backitems[i][1] for i in range(0,len(backitems))] 
#find the timestamp that has connecttions    
def count_times(userId,newsId,timeStamp,filename,publishtime):
    data_path = 'E:/MyWork/Python/'  
    filepath = data_path+filename+'.txt'        # the next step use the "alltimescan.txt"
    mapping = {}   
    with open(filepath,'rb') as f:
        count = 0.0
        for e,line in enumerate(f):
            line = line.split('\t')

            if(e == 0):
                t_user_Id = ''
                t_news_Id = ''
                t_second = 0.0
            user_Id = line[0]
            timestamp = line[2]
            if(timestamp == timeStamp):
                if(user_Id == t_user_Id ):
                       count +=1
                       try:
                           mapping[t_news_Id] += 1
                       except KeyError:
                           mapping[t_news_Id] = 1
                           
            t_user_Id = user_Id
            t_news_Id = news_Id
            t_second = second
    #recommendthe most pupular news
    result = []
    r_news = sort_by_value(mapping)[-1:]
    r_news2 = sort_by_value(mapping)[-1:]
    for news in r_news:
        conference = mapping[news]/count
        if(  conference >= 0.1):
            result.append(news)
    if(len(result)==0):
        popular(userId,newsId,timestamp,Pubtime)
        #for news in r_news2:
          #result.append(news)
    return result,count,mapping

#find the prior news
def count_news(userId ,newsId ,timeStamp,filename,publishtime):
    data_path = 'E:/MyWork/Python/'  
    filepath = data_path+filename+'.txt'        # the next step use the "alltimescan.txt"
    mapping = {}   
    with open(filepath,'rb') as f:
        #use to calculate the all number
        count = 0.0
        for e,line in enumerate(f):
            line = line.split('\t')
            #a temporary flag
            if(e == 0):
                t_user_Id = ''
                t_news_Id = ''
                t_second = 0.0
            user_Id = line[0]
            news_Id = line[1]
            second = float(line[2])
            if(news_Id == newsId):
                if(user_Id == t_user_Id ):
                       count +=1
                       try:
                           mapping[t_news_Id] += 1
                       except KeyError:
                           mapping[t_news_Id] = 1
                           
            t_user_Id = user_Id
            t_news_Id = news_Id
            t_second = second
    #recommendthe most pupular news
    result = []
    r_news = sort_by_value(mapping)[-1:]
    r_news2 = sort_by_value(mapping)[-1:]
    for news in r_news:
        conference = mapping[news]/count
        if(  conference >= 0.1):
        result.append(news)
    if(len(result)==0):
        news = popular(userId,newsId,timeStamp,publishtime)
        result.append(news)
    return result,count,mapping
    
def popular(userId,newsId,timeStamp,Pubtime):
    data_path='E:/MyWork/Python/alltimescan.txt'
    map={}
    count=0
    result=[]
    with open (data_path,'rb') as f:
        for e, line in enumerate(f):
            line = line.split('\t')
            userid = line[0]
            newsid = line[1]
            timestamp=line[2]
            time =line[4]
            year = line[4][0:4]
           
            if Pubtime[0:4] !='NULL' and year != 'NULL':
                Publishtime=Pubtime[0:14]
                publishtime=time[0:14]
                #print publishtime
                if publishtime == Publishtime:
                    if userid != userId :
                        try:
                            map[newsid]+=1
                        except KeyError:
                           map[newsid] = 1
                        #print count
            elif timestamp == timeStamp and userid != userId:
                print 'done'
                try:
                    map[newsid]+=1
                except KeyError:
                    map[newsid] = 1
        if len(map)!=0:
            result=sort_by_value(map)[-1:]
        else: 
            result=[newsId]
    return result[0]
#find the last click news
def new_Id_time_Data():
    data_path = 'E:/MyWork/Python/'
    raw_file = data_path + 'flagnum.txt'       #data of need
    left_user = data_path + 'step11_6.txt'     #output data.txt
    allmappingfile = data_path + 'allmapping6.txt'  # func of count_news()'s data
    output_file = data_path +'step11_6.csv'  #doutput data
    o = open( output_file, 'wb' )   # write into the final file
    writer = csv.writer( o)  
    with open (raw_file,'rb') as f: 
        with open(left_user,'wb') as l:
            with open(allmappingfile,'wb') as a:
                head= ['userid','newsid']
                temp = []
                writer.writerow( head )
                for e,line in enumerate(f):
                    line = line.split('\t')
                    user_Id = line[0]
                    news_Id = line[1]
                    timestamp = line[2]
                    r_news = []
                    scantime = line[3]
                    publishtime = line[4]
                    r_news,count,mapp= count_news(user_Id,news_Id,timestamp,'alltimescan',publishtime)
                    if(len(r_news)==0 ):
                        #print popular(user_Id,news_Id,scantime,publishtime)
                        rnews1,count1,mapp1 = count_times(user_Id,news_Id,timestamp,"alltimescan",publishtime)
                        print rnews1
                        for news in rnews1:
                            r_news.append(news)
                        l.write(user_Id+'\t'+news_Id+'\t'+timestamp+'\t'+line[3]+'\t'+line[4]+'\n')
                    for i in r_news:

                        result=[user_Id]
                        result.append(i)

                        writer.writerow(result) 
               # print len(result)
#                    if(e > 100):
#                        break
    print len(temp)
    l.close()
    f.close()
    o.close()  
new_Id_time_Data()
