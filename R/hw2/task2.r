
#  W ASD
# 61 793

# P(6 > X >= 2) = P(2 <= X <= 5), X ~ Ge(p = 21/90)
geomProb = 21 / 90;
ans1 = pgeom(5, geomProb) - pgeom(1, geomProb);
ans1;

# P(min{3, A} < Y ≤ W + 4) за сл. в. Y ~ Po(D + 1.5)
# P(4 <= Y <= 5), Y ~ Po(4.5)
lambda = 4.5
ppois(5, lambda) - ppois(3, lambda)
