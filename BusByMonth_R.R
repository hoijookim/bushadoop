br = readLines("D:/Hadoop/bbmResult.txt", encoding = "UTF-8")
br = strsplit(br, "\t")

month = c() 
count = c()


for (line in br) {
  if (line[1] == '02') {
    count[length(count) + 1 ] = as.numeric(line[2]) / 141
  } else if(line[1] =='01' || line[1] == '03' || line[1] == '05' ||
            line[1] =='07' || line[1] == '08' || line[1] == '10' ||
            line[1] == '12'){
    count[length(count) + 1 ] = as.numeric(line[2]) / 155
  } else if(line[1] =='04' || line[1] == '06' || 
            line[1] =='09' || line[1] == '11'){
    count[length(count) + 1 ] = as.numeric(line[2]) / 150
  }
  month[length(month) + 1 ] = as.numeric(line[1])
}

bd = data.frame(MONTH=month, COUNT=count)

# 꺾은선 그래프
plot(bd$MONTH, bd$COUNT,
     type = 'o',  
     main = '2015 ~ 2019 버스',
     xlab = '월', ylab = '하루 평균 이용객 수',
     col = '#43A047', lwd = '3'
)