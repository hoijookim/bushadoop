br = readLines("D:/Hadoop/bhResult.txt", encoding = "UTF-8")
br = strsplit(br, "\t")

no = c()
count = c()

for (line in br) {
  no[ length(no) + 1 ] = line[1]
  count[ length(count) + 1 ] = as.numeric(line[2])
}

bd = data.frame(NO=no, COUNT=count)
library('wordcloud2')
bd = bd[order(-bd$COUNT), ]
wordcloud2(bd, color='random-dark')
