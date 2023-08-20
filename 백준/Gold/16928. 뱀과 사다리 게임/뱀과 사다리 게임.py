import sys
from collections import deque

input=sys.stdin.readline
N,M=map(int,input().split())
visited=[0]*101
board=[i for i in range(101)]

for _ in range(N):
    a,b=map(int,input().split())
    board[a]=b
for _ in range(M):
    a, b = map(int, input().split())
    board[a] = b


def bfs(x):
    queue=deque()
    queue.append(x)
    visited[x]=1
    while queue:
        x=queue.popleft()
        for i in range(1,7):
            nx=x+i
            if nx>100:
                continue

            next=board[nx]
            if not visited[next]:
                queue.append(next)
                visited[next]=visited[x]+1
                if next==100:
                    return


bfs(1)
print(visited[100]-1)
