male(shankar).
male(ulhas).
male(satish).
male(saurabh).
male(prashant).

female(umabai).
female(mrunal).
female(sadhana).
female(swati).

parent(shankar,umabai,ulhas).
parent(shankar,umabai,satish).
parent(ulhas,mrunal,prashant).
parent(satish,sadhana,saurabh).
parent(satish,sadhana,swati).

brother(ulhas,satish).
brother(satish,ulhas).
brother(prashant,saurabh).
brother(saurabh,prashant).
sister(swati,saurabh).
sister(swati,prashant).

father(X,Y) :- parent(X,Z,Y).
mother(X,Y) :- parent(Z,X,Y).

son(X,Y,Z) :- male(X),father(Y,X),mother(Z,X).
daughter(X,Y,Z) :- female(X),father(Y,X),mother(Z,X).

wife(X,Y) :- female(X),parent(Y,X,Z).

grandfather(X,Y) :- male(X),father(X,Z),father(Z,Y).

uncle(X,Y) :- male(X),brother(X,Z),father(Z,Y).

aunt(X,Y) :- wife(X,Z),uncle(Z,Y).

cousin(X,Y) :- father(Z,X),brother(Z,W),father(W,Y).

ancestor(X,Y,Z) :- parent(X,Y,Z).
ancestor(X,Y,Z) :- parent(X,Y,W),ancestor(W,U,Z).