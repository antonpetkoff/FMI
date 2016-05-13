function stringfourier1
clc;
clf;
a = 0.5;
L = pi * sqrt(2);
tmax = 30;
t = 0:tmax/1000:tmax;
x = 0:L/100:L;

    function y = phi(x)
%         % a)
%         for i=1:length(x)
%             if 1 < x(i) && x(i) < 2
%                 y(i) = 10 * exp(4/((2*x(i)-3)^2 -1));
%             else
%                 y(i) = 0;
%             end
%         end

%         % б)
%         y = sin(3*x*sqrt(2));
        
        % в)
        y = 0
    end

    function y = psi(x)
%           % a) и б)
%         y = 0 * x;
        % в)
        y = 1/4 * sin(5*x*sqrt(2))
    end

    function y = fourier(x, t)
        y = 0;
        for k=1:tmax % replace tmax with 30 if errors
            Xk=sin(k*pi*x/L);
            Ak=(2/L)*trapz(x, phi(x).*Xk);
            Bk=(2/(a*k*pi))*trapz(x, psi(x).*Xk);
            Tk=Ak*cos(a*k*pi*t/L) + Bk*sin(a*k*pi*t/L);
            y = y + Tk*Xk;
        end
    end

for n=1:length(t)
   plot(x, fourier(x, t(n)));
   axis([0, L, -0.5, 0.5]);
   M(n)=getframe;
end
movie(M,2)

% % subplots
% tt = [0, 15, 20];
% for m = 1:3
%     subplot(3, 1, m);
%     plot(x, fourier(x, tt(m)));
% 
%     axis([0, L, -0.5, 0.5]);
% end


end
