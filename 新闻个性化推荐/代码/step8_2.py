# -*- coding: utf-8 -*-
"""
Created on Sun Oct 12 10:20:35 2014

@author: zhan
"""
import  csv

# this is the 8th step of the code ,we divide the data into 31 parts by the scan time to 
#solve the left user

# d sorted by value
def sort_by_value(d): 
    items=d.items() 
    backitems=[[v[1],v[0]] for v in items] 
    backitems.sort() 
    return [ backitems[i][1] for i in range(0,len(backitems))] 
#find the prior news
def count_news(userId ,newsId ,filename):
    data_path = 'E:/datamining/SecCompection/'
    filepath = data_path+'timeparted2/'+filename+'.txt'
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
                if(user_Id == t_user_Id):
#                    timespan = t_second - second
#                    if(timespan>=0.0 and timespan <3600.0 ):
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
        for news in r_news2:
          result.append(news)
    return result,count,mapping
    

#find the last click news
def new_Id_time_Data():
    data_path = 'E:/datamining/SecCompection/'
    raw_file = data_path + '1539.txt'
    left_user = data_path + 'step8_2.txt'
    allmappingfile = data_path + 'allmapping.txt'
    output_file = data_path +'step8_2.csv'
    o = open( output_file, 'wb' )
    writer = csv.writer( o)
    with open (raw_file,'rb') as f:
        with open(left_user,'wb') as l:
            with open(allmappingfile,'wb') as a:
                head= ['userid','newsid']
                temp = []
                writer.writerow(  head )
                for e,line in enumerate(f):
                    line = line.split('\t')
                    user_Id = line[0]
                    news_Id = line[1]
                    timestamp = line[2]
                    r_news = []
                    flagnews = float(line[3][8:10])
                    scantime = line[3][5:7]+line[3][8:10]
                    if(flagnews >= 1 and flagnews <=5):
                        r_news,count,mapp= count_news(user_Id,news_Id,'0105')
                    elif(flagnews >= 6 and flagnews <=10):
                        r_news,count,mapp= count_news(user_Id,news_Id,'0610')
                    elif(flagnews >= 11 and flagnews <=15):
                        r_news,count,mapp= count_news(user_Id,news_Id,'1115')
                    elif(flagnews >= 16 and flagnews <=20):
                        r_news,count,mapp= count_news(user_Id,news_Id,'1620')
                    elif(flagnews >= 21 and flagnews <=25):
                        r_news,count,mapp= count_news(user_Id,news_Id,'2125')
                    else:
                        r_news,count,mapp= count_news(user_Id,news_Id,'2631')
                    for i in mapp:
                        temps =i +'\t'+ str(mapp[i])
                        a.write(user_Id + '\t'+temps + '\n')  
                    if(len(r_news)==0 ):
                        r_news.append(news_Id)
                        l.write(user_Id+'\t'+news_Id+'\t'+timestamp+'\t'+line[3]+'\t'+line[4]+'\n')
#                        l.write(user_Id+'\n')
#                        continue
                    for i in r_news:
                        result=[user_Id]
                        result.append(i)
                        writer.writerow(result)    
#                    if(e > 100):
#                        break
    print len(temp)
    l.close()
    f.close()
    o.close()  
new_Id_time_Data()