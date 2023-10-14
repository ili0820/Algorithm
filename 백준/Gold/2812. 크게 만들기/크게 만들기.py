import sys
input=sys.stdin.readline
n,k=map(int,input().split())

number=list(input().strip())

stack=[]
for numb in number:
    while stack and stack[-1]<numb and k:
        stack.pop()
        k-=1
    stack.append(numb)

print("".join(stack[:-k]) if k else "".join(stack))
