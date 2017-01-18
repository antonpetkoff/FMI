library(UsingR)

# task 1
# H0: mu_m == mu_e
# Ha: mu_m != mu_e
blood
qt(0.1 / 2, length(blood) - 1)
qt(0.1 / 2, length(blood) - 1, lower.tail = FALSE)
t.test(blood$Machine, blood$Expert, mu = 0, alt = "two.sided", sig.level = 0.1)

# task 2

