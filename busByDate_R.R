br = readLines("D:/Hadoop/bbdResult.txt", encoding = "UTF-8")
br = strsplit(br, "\t")

yoil = c() 
count = c()

for (line in br) {
  yoil[length(yoil) + 1 ] = line[1]
  count[length(count) + 1] = as.numeric(line[2])
}

bd = data.frame(YOIL=yoil, COUNT=count)

# 문자열 붙이기
# paste("ㅋ", "ㅎ", "ㅇ", ..)

# round(값, 소수점자리)

pie(bd$COUNT, labels = paste(bd$YOIL, round(bd$COUNT / sum(bd$COUNT) * 100, 2), "%"), 
    main = "요일별 버스", col = rainbow(7), border = NA)