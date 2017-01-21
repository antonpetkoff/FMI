library(UsingR)

# task 1
# H0: mu_m == mu_e
# Ha: mu_m != mu_e
blood
alpha = 0.1
df = length(blood) - 1
qt(alpha / 2, df)
qt(alpha / 2, df, lower.tail = FALSE)
# rejection region is (-Inf, -6.313752) U (6.313752, Inf)

m = blood$Machine
e = blood$Expert

# are the variances equal? no
var(m)
var(e)

# normality of data - they seem normal to me...
hist(m)
hist(e)

# test statistic
(mean(m - e) * sqrt(length(m)))/sd(m - e)

# p-value
2 * pt(q = 0.681617, df = 14, lower.tail = FALSE)

t.test(blood$Machine, blood$Expert, paired = TRUE)
