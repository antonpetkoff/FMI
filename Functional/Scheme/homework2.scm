(define-syntax cons-stream
    (syntax-rules () ((cons-stream h t) (cons h (delay t))) )
)
(define (head s) (car s))
(define (tail s) (force (cdr s)))
(define (first! n s)
    (if (or (= n 0) (null? s))
        '()
        (cons (head s) (first! (- n 1) (tail s)))
    )
)

(define ops (cons-stream + (cons-stream - ops)))

(define (zad1 ls)
    (define (helper ops l accum)
        (if (null? l)
            accum
            
        )
    )
    (helper (first! (- (length l) 1) ops) ls 0)
)