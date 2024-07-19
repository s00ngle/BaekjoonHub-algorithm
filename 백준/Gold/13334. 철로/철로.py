import heapq

n = int(input())
roads = []

for _ in range(n):
    l, r = map(int, input().split())
    roads.append([min(l, r), max(l, r)])

d = int(input())

roads.sort(key = lambda x: x[1])

cnt = 0
pq = []

for l, r in roads:
    while pq and pq[0][0] < r - d:
        heapq.heappop(pq)

    if r - l <= d:
        heapq.heappush(pq, [l, r])
        cnt = max(cnt, len(pq))

print(cnt)