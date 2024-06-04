# 1. 프로젝트 제작 목적
+  스프링, 스프링 부트, JPA, 시큐리티 등의 기술스택들을 이론적으로 학습한 뒤에 실제로 어떻게 동작하는지 이해하기 위해서 간단한 쇼핑몰 프로젝트를 구현해봄.
  흔한 쇼핑몰 프로젝트일지라도 완성하는 것에 초점을 두지 않고, 사용되는 기술에 대한 정확한 개념 및 사용법, 그리고 동작원리에 대해서 초점을 맞추었고,
  또한 전체적인 흐름 파악을 목표로 두었으며 프론트보다는 백엔드에 좀 더 집중하였음.
***
# 2. 프로젝트 개발 환경
+  운영체제 : Window11
+  통합개발환경(IDE) : IntelliJ
+  JDK 버전 : JDK17
+  스프링 부트 버전 : SpringBoot 3.2.5
+  데이터베이스 : MariaDB
+  빌드 툴 : Gradle
+  관리 툴 : Git, GitHub
***
# 3. 프로젝트 기술 스택
+  Front-End
    +  HTML, CSS, JS, BootStrap, Thymeleaf
+ Back-End
    +  Spring Boot, Spring Secutiry, Spring Data JPA
+  DataBase
    +  Hibernate, MariaDB
***
# 4. 프로젝트 구현 기능
+  회원(Member)
    +  회원가입 / 로그인 및 로그아웃
+  상품(Item)
    +  상품 등록 / 상품 관리 / 상품 수정 / 상품 조회 / 상품 상세 페이지
+  주문(Order)
    +  상품 주문 / 주문 내역 조회 / 주문 취소
+  장바구니(Cart)
    +  장바구니 담기 / 장바구니 조회 / 장바구니 삭제 / 장바구니 상품 주문
***
# 5. DataBase 모델링
![1](https://github.com/rlarmsdn2999/SpringBootWebProject_Shopping-Mall/assets/110002604/d1d597f0-5354-4ff0-b6cd-d3e280ca632d)
+  member - 쇼핑몰 회원 정보 테이블
+  cart - 회원의 장바구니 목록 테이블
+  cart_item - 장바구니에 담긴 상품 정보 테이블
+  orders - 쇼핑몰 회원들의 주문 목록 테이블
+ order_item - 쇼핑몰 상품 정보 테이블
+ item - 쇼핑몰 상품 정보 테이블
+ item_img - 상품에 대한 이미지 정보를 담고 있는 테이블
***
# 6. API 명세서
![2](https://github.com/rlarmsdn2999/SpringBootWebProject_Shopping-Mall/assets/110002604/e047074e-1090-403f-ae7f-92a3bf7340f7)
