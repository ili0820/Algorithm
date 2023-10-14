import sys
input=sys.stdin.readline
N=int(input())
total=0
for a in range(1,N):
    total+=(a*N)+a
print(total)