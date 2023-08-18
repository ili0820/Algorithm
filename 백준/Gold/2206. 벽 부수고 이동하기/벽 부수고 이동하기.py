import sys
from collections import deque
input=sys.stdin.readline
N,M=map(int,input().split())

graph=[list(map(int,input().rstrip())) for i in range(N)]
visited=[[[0] * 2 for _ in range(M)] for _ in range(N)]

def bfs():

    dx=[0,0,1,-1]
    dy=[1,-1,0,0]
    
    q=deque()
    q.append((0,0,0))
    visited[0][0][0]=1
    while q:
        x,y,wall=q.popleft()
        if x==N-1 and y==M-1:
            return visited[x][y][wall]
        for i in range(4):
            nx=x+dx[i]
            ny=y+dy[i]

            if nx< 0 or ny<0 or nx>=N or ny>=M or visited[nx][ny][wall]:
                continue
            if visited[nx][ny][wall]==0 and graph[nx][ny]==0:
                visited[nx][ny][wall]=visited[x][y][wall]+1
                q.append((nx,ny,wall))
            if wall==0 and graph[nx][ny]==1:
                q.append((nx,ny,1))
                visited[nx][ny][1]=visited[x][y][wall]+1
                
    return -1

print(bfs())
