library(UsingR)
brightness

simple.eda(brightness)
# the sample seems normal and symmetric to me...

# we don't know the variance, so we use t-test
t.test(brightness, conf.level = 0.93)

# the median and the mean match when the sample is symmetric!
wilcox.test(brightness, conf.int = TRUE, conf.level = 0.93)

# from Wikipedia Skewness#Relationship of mean and median:
# If the distribution is symmetric, then the mean is equal to the median,
# and the distribution has zero skewness. If, in addition,
#the distribution is unimodal, then the mean = median = mode.

mode = function(x) {
  ux = unique(x)
  ux[which.max(tabulate(match(x, ux)))]
}

median(brightness)
mode(brightness)
mean(brightness)
