library(UsingR);

lambda = 1 / 8;
expectation = 1 / lambda;

cltExp = function (lambda, n) {
  sample = rexp(n, lambda);
  (mean(sample) - expectation) * lambda * sqrt(n); 
};

observe = function (simulations, sampleSize) {
  simple.sim(simulations, cltExp, lambda, sampleSize);
};

observe10 = observe(500, 10);
hist(observe10);
qqnorm(observe10);
qqline(observe10);

observe1000 = observe(500, 1000);
hist(observe1000);
qqnorm(observe1000);
qqline(observe1000);
