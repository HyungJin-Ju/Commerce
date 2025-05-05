# 프로젝트명
- 이 프로젝트는 섹션별 상품 리스트를 페이징과 위시리스트로 보여주는 Android 앱입니다.

## 기술 스택
- Kotlin + Jetpack Compose
- Paging 3
- Hilt (DI)
- MVVM + Clean Architecture
- Pull-to-Refresh

## 주요 기능
- 섹션 리스트 페이징 처리
- 섹션별 상품 동적 로딩
- 위시리스트 추가/제거
- Pull-to-Refresh 지원
- 할인율, 가격 포맷 처리

## 모듈
- app (엔트리 포인트)
- core (공용 유틸리티 포함)
- domain (UseCase, Entity 정의)
- data (Repository 구현, Local/Remote Source)
- presentation (Jetpack Compose 기반 UI 및 ViewModel)
