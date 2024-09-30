# TAB QC APP

<img width="80" src="https://github.com/user-attachments/assets/d96e4a3a-c777-4027-9735-d07f26a15678">

2023년 현재 B2B 태블릿 매출 1위 회사 태클라스트 코리아의 **TAB QC APP**입니다.(현재 실사용 되고 있음)

<p align="center">
<img src="https://github.com/user-attachments/assets/28068e34-a0d6-47fb-a16f-6bc74d5c7d21" width="16%" height="30%">
<img src="https://github.com/user-attachments/assets/95ed5b3e-40ef-4310-b5b4-cffde848321f" width="16%" height="30%">
<img src="https://github.com/user-attachments/assets/d48cfc92-c9db-4224-b7a2-e2196e05cef0" width="16%" height="30%">
<img src="https://github.com/user-attachments/assets/d02394c1-ec24-47a0-9e2c-d020700e1570" width="16%" height="30%">
<img src="https://github.com/user-attachments/assets/ae6cb789-5cc4-407d-aae5-f6c794b81a07" width="16%" height="30%">
</p>


# OverView
> 하루에 600대 이상의 태블릿을 공급하는 회사 태클라스트 코리아의 더 빠르고 효율적인 QC를 위한 어플리케이션
> CPU, GPU, ... 모든 물리 QC가 가능한 앱
> 작업자들을 위한 자동화 QC 프로세스 마련(실제 작업량을 총평균 30% 감소)
<-- > PULL Architecture를 이용해 커스텀 QC를 진행 가능(서버에서 QC 결과를 DB에 저장하고 AI를 통한 분석으로 최적의 QC 검사 순서/기준을 QC APP에 제공) --!>

- 개발 기간 : 2023.04.3 ~ 2023.07.3
- 논문 작성 및 출판 기간 : 2023.11 ~ 2024.11 
- Android 개발자 : <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=Android&logoColor=white"> <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=Kotlin&logoColor=white">

# About

### Features
- 0. 안드로이드 디바이스에서 확인 가능한 모든 QC 검사 진행
- 1. 집안일  
- 2. 집안일 조회
- 3. 집안일 삭제  
- 4. 그룹 생성 , 카카오톡 초대 , 딥링크  
- 5. 프로필 수정 
- 6. 집안일 규칙 추가
- 7. 푸쉬 알림
- 8. 네트워크 연결 감지

### Technology Stack
- Tools : Android Studio Dolphin
- Language : Kotlin
- Architecture Pattern : MVVM Pattern
- Android Architecture Components(AAC)
  - Flow
  - ViewModel
  - Coroutine
  - Data Binding
  - Hilt
- Naivgation Conponponent
- FirebaseMessagingService
- OKHTTP
- RETROFIT
- MOSHI
- SERIALIZATION
- KAKAO_SDK
- GLIDE
- FIREBASK_BOM
- TIMBER 

### Foldering
```
.
├── base
├── data
│   ├── dataSource
│   ├── repository
│   └── ApiService
├── di
│   ├── NetworkModule
│   └── RepositoryModule
├── model
│   ├── enums
│   ├── request
│   └── response
├── service
│   ├── FairerFirebaseMessagingService
│   └── InternetService
├── ui (for features)
└── util
```

# WireFrame
<img width="400" alt="스크린샷 2022-06-29 오전 12 58 34" src="https://user-images.githubusercontent.com/27774564/176225988-3c2a3b19-53a0-4627-89c8-1e808e2ec43b.png">
<img width="400" alt="스크린샷 2022-06-29 오전 12 58 42" src="https://user-images.githubusercontent.com/27774564/176226010-fd300d9c-30dd-4da1-b278-7354ecffb6e4.png">
<img width="400" alt="스크린샷 2022-06-29 오전 12 59 24" src="https://user-images.githubusercontent.com/27774564/176226122-5eb5603a-cfec-40e4-bed2-cd2d0c2167a4.png">
<img width="400" alt="스크린샷 2022-06-29 오전 12 59 38" src="https://user-images.githubusercontent.com/27774564/176226168-12852d78-4506-4f09-9ef2-830a3ade38c4.png">


# ToDo
- 메인 화면 개편
- 룰렛 기능
- 통계 기능
