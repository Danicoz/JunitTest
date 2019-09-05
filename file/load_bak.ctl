OPTIONS(skip=1) LOAD DATA CHARACTERSET ZHS16GBK 
INFILE './file/É¾³ýÊý¾Ý.txt'  
INTO TABLE T_TRANS_ROAD_DATA 
APPEND FIELDS TERMINATED BY "," 
optionally enclosed by "\n" 
trailing nullcols(ID,CITY_NAME,SEG_NAME,type )