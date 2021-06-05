# SimulatedAnnealing ParameterAstimate

* 모델 선정에 어려움이 있었습니다. 결국, 제 경험을 바탕으로 토익 공부 일수에 따른 토익 점수 분포로 선정하였습니다.

## Data
---

| 공부 일수 | 토익 점수 | 
|:------:|:-------:|
| 25 | 350 | 
| 50 | 600 |
| 100 | 750 | 
| 150 | 900 |
## Data, Model Setting
---

![데이터, 모델 설정](https://postfiles.pstatic.net/MjAyMTA2MDZfMTk4/MDAxNjIyOTEzMzYwMDUx._Yfs0Q5gYDuUprr1YGTmkSnhzyay3s-XsnpRz3nOuB0g.Nlnz533aCupMO-TV6nwR9EnMDZraOlMIi64x0knCUmUg.PNG.ljk1256/%EC%A0%90%EC%88%98%EB%B6%84%ED%8F%AC_%EB%8D%B0%EC%9D%B4%ED%84%B0.png?type=w580)

* 각 점은 일수에 따른 점수 분포를 나타낸 것이다.
* 직선은 데이터들의 회귀식이다. 
* 회귀식 : y = 4x + 300

## Code Review
---

```java
public static void main(String[] args) {
		 SimulatedAnnealing sa = new SimulatedAnnealing(1, 0.97, 1000);

		    sa.solve(new Problem() {
	            @Override
	            public double fit(double a, double b) {
	                return 10000*a*a + 200*a*b + b*b - 140000*a - 1400*b + 490000 ;
	            }

	            @Override
	            public boolean isNeighborBetter(double f0, double f1) {
	                return f1 > f0;
	            }
	        }, 1, 10);

	        System.out.println(sa.hist);
	        System.out.println(sa.hista);
	        System.out.println(sa.histb);
	    }
```

* 기본적인 구조는 강의 시간에 구현 된 코드와 같습니다.
* 추가적으로 변경된 부분은 적합도 판단을 위한 수식, 최적화 된 값을 찾기 위한 매개변수 조정
* 결론적으로 curve fitting 을 하는 cost funtion 이 최소가 될때, parameter (a, b) 값이 실제 값과 얼마나 일치하는지 비교

## Parameter Astimate
---

![데이터, 모델 설정](https://postfiles.pstatic.net/MjAyMTA2MDZfMTYz/MDAxNjIyOTEzMzcyNTcy.oce7_RSNkeht_IioGCqHWKGbsg5HgSQ2ZiJ1VxbJDC0g.pmsHooilvIE_t-ofHXwX--rAp5LCjUS9HdBiutt1keIg.PNG.ljk1256/%EA%B2%B0%EA%B3%BC_%EA%B0%92.png?type=w580)

* 위 데이터는 SimulatedAnnealing 을 통한 cost function, parameter (a, b) 값 이다.
* 두 번째 값을 보면 거의 근사 한 것을 볼 수 있다.
* 오차를 생각 한다면, 실제 값 a = 4, b = 300 과 유사한 것을 알 수 있다.

## Source Code Link
---

* https://github.com/ljk1256/simulatedAnnealing_parameterAstimate
