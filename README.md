# Kurly Android Assignment

## 🛠️ 사용 기술 및 라이브러리

- Kotlin
- Hilt, MVVM
- Compose
- Coroutines
- Ktor, OkHttp, KotlinSerialization
- DataStore

## Feature

### 메인 화면 ###

- 앱의 메인 화면이며 상품 리스트를 보여줍니다.
- 각 상품 별 찜하기가 가능합니다.
- 상품리스트는 섹션별로 나뉘어 보여집니다.
- 상단 스크롤 시 상품이 새로고침됩니다.
- 하단 스크롤 시 다음 상품이 보여집니다.

## Core

### Common ###

- 공통적으로 필요한 데이터를 담당합니다.

### UI ###

- UI 데이터를 담당합니다.

### Domain ###

- ViewModel로부터 요청받은 데이터를 처리합니다.

### Data ###

- 모든 데이터의 처리를 담당합니다.
- Local 또는 Remote 레이어에 전달받은 작업을 요청합니다.

### Local ###

- 보관요청된 데이터를 SharedPreference에 저장합니다.
    - 데이터를 Flow로 쉽게 처리하기위해 DataStore를 사용하였습니다.

### Remote ###

- 서버와의 통신을 담당합니다.

### MockServer ###

- 가상 서버의 데이터를 제공합니다.