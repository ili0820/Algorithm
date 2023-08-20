import sys
input =sys.stdin.readline
N=int(input())
graph=[list(map(int,input().split())) for _ in range(N)]


result = []

def solution(x, y, N) :
  color = graph[x][y]
  for i in range(x, x+N) :
    for j in range(y, y+N) :
      if color != graph[i][j] :
        solution(x, y, N//3)
        solution(x, y+N//3, N//3)
        solution(x, y+2*N//3, N//3)

        solution(x + N//3, y, N//3)
        solution(x + N//3, y+N//3, N//3)
        solution(x + N//3, y+2*N//3, N//3)

        solution(x + 2*N//3, y, N//3)
        solution(x + 2*N//3, y+N//3, N//3)
        solution(x + 2*N//3, y+2*N//3, N//3)


        return
  if color == -1 :
    result.append(-1)
  elif color ==1 :
    result.append(1)
  else:
    result.append(0)
solution(0,0,N)
print(result.count(-1))
print(result.count(0))
print(result.count(1))