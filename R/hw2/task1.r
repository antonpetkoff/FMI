library(UsingR);

lambda = 1 / 8;

sampleMean = function (n, lambda) {
  mean(rexp(n, lambda)); # mu = sigma = 1 / lambda = 8
};

observe = function (sampleSize, simulationCount) {
  simple.sim(simulationCount, sampleMean, sampleSize, lambda);
};

observe10 = observe(10, 500);
hist(observe10);
qqnorm(observe10);
qqline(observe10);
# var(observe10) # =? 1/(sigma * sqrt(n)) = 1 / (8 * sqrt(10))

observe1000 = observe(1000, 500);
hist(observe1000);
qqnorm(observe1000);
qqline(observe1000);
