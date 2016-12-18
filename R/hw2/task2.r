
#  W ASD
# 61 793

# а) P(6 > X >= 2) = P(2 <= X <= 5), X ~ Ge(p = 21/90)
geomProb = 21 / 90;
ans1 = pgeom(5, geomProb) - pgeom(1, geomProb);
ans1;

# б) P(min{3, A} < Y ≤ W + 4) за сл. в. Y ~ Po(D + 1.5)
# P(4 <= Y <= 5), Y ~ Po(4.5)
lambda = 4.5
ppois(5, lambda) - ppois(3, lambda)

# в) стойността z* така че P(-z* < Z < z*) = (W + A + S + D + 11)/111 за Z ~ N(0, 1)
# P(-z* < Z < z*) = 0.2792793 за Z ~ N(0, 1)
# zProb = 0.2792739
zProb = (1 + 7 + 9 + 3 + 11)/111
zQuantile = abs(qnorm((1 - zProb) / 2))

# check
testProb = 1 - pnorm(zQuantile, lower.tail = FALSE) - pnorm(-zQuantile)
abs(zProb - testProb) <= 1e-14

# WIP: г) стойността x, така чe P(−1.5 < T <= x) = (55 + W + A)/100 за Т ~ t(33)
#  P(−1.5 < T <= x) = (55 + 1 + 7)/100 за Т ~ t(33)
tProb = (55 + 1 + 7)/100
?pt
lowerTail = pt(-1.5, 33)
tQuantile = qt(lowerTail + tProb, 33)
testProb = pt(tQuantile, 33) - pt(lowerTail, 33)

