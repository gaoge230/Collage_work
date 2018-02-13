# -*- coding: utf-8 -*-
# -*- coding: cp936 -*-
"""
Created on Mon Sep 22 21:28:12 2014

@author: zhan
"""

from time import time
def main():
    

   
    
    t0 = time()
    user = ['5218791']
    newId = ''
    temp = ''
    data_path = 'E:/datamining/SecCompection/'
    train_file = data_path+'news.txt'
    pre_file = data_path + 'predeal1'
    f = ''
    with open( train_file, 'rb' ) as f:
       with open( pre_file, 'wb' ) as sub_file:
        for e, line in enumerate(f):
            line = line.split( '\t' )
            temp = newId
            newId = newId + line[1] + ' '
            if line[0] not in user:
                user.append(line[0])
                
                sub_file.write(user[-2]+'\t'+temp+'\n')   
                newId = ''+line[1]+' '
            
            line[-1] = line[-1][:-1]
          
       
            
               
        print("done in %fs" % (time() - t0))
        print len(user)
        
        
    
   
   

if __name__ == '__main__':
    main()
    