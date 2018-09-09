Web-based content
=
 

## 1. 개요

 
웹 컨텐츠를 일반 브라우저나 안드로이드 앱(웹뷰를 사용)으로 제공 가능합니다.

웹 컨텐츠의 나머지 부분과 동일한 사용자 경험을 유지하고 싶으면 [앱 링크](https://developer.android.com/training/app-links/) 혹은 [검색](https://developer.android.com/guide/topics/search/) 기능을 구현하고.

구글 플레이 서비스 기반 경험을 제공하고 싶으면 [앱 액션](https://developer.android.com/guide/actions/) 혹은 [슬라이스](https://developer.android.com/guide/slices) 기능을 사용하십시오.
 
그러나 일부 앱의 경우 UI에 대한 제어 권한을 높여야 할 수도 있습니다. 이 경우, Webiew는 신뢰할 수 있는 제1자 콘텐츠를 표시하기 위한 좋은 옵션입니다.

WebView 프레임워크를 사용하면 웹 페이지를 모든 주요 웹 브라우저의 모든 화면 구성에 적합한 크기 및 축척(스케일)으로 표시할 뷰 포트 및 스타일 프로퍼티를 지정할 수 있습니다. AndroidAPI를 웹 기반 애플리케이션에 제공하는 웹 페이지의 JavaScript에서 API를 호출할 수 있도록 Android앱과 웹 페이지 간의 인터페이스를 정의할 수도 있습니다.

하지만 단순히 웹 사이트를 보기 위한 수단으로 Android앱을 개발해서는 안 됩니다. 대신, 앱에 포함하는 웹 페이지는 해당 환경에 맞게 특별히 디자인되어야 합니다.


* 웹뷰의 대안

웹뷰가 UI 에 대한 향상된 컨트롤을 제공한다고 해도 유사한 기능을 제공하고 더 적은 구성으로 비슷한 기능을 하는 아래 대안이 있습니다.

If you want to send users to a mobile site, [build a progressive web app (PWA)](https://codelabs.developers.google.com/codelabs/your-first-pwapp/#0).

If you want to display occasional third-party web content, [send an intent to installed web browsers](https://developer.android.com/guide/components/intents-common#Browser).

If you want to provide users with faster page loading, improved privacy protections, and better security than WebView provides, consider using [Chrome Custom Tabs](https://developer.chrome.com/multidevice/android/customtabs).


## 2. 웹뷰에서 웹앱 만들기

웹 어플리케이션을 보여주기 원한다면 웹뷰를 사용하시면 됩니다. 이것은 View 의 하위 클래스입니다. 여기엔 네비게이션 컨트롤, 주소 표시줄 같은 상용 웹 브라우저의 기능은 포함되지 않습니다.
 
웹뷰를 사용하는 일반적인 시나리오는 사용자 가이드나 사용자 계약서 같은 앱 업데이트 없이 수정해야하는 정보를 제공할 경우 웹뷰를 사용하는 방법에 유용합니다. 위 문서가 있는 웹 페이지의 주소를 사용하여 웹뷰로 보여줍니다.
 
또 다른 시나리오는 이메일과 같은 앱처럼 데이터를 검색하기 위해 항상 인터넷에 연결되어 있어야 하는 사용자에게 데이터를 제공하는 경우입니다. 네트워크 요청을 하고 그것을 파싱하고 데이터를 분석하고... 하는 대신 웹뷰를 사용할 수 있습니다.
 

* 웹뷰 추가

레이아웃xml 에 웹뷰 추가 -> id 로 뷰를 할당하고 loadUr
혹은

웹뷰 인스턴스를 생성하고 액티비티의 뷰로 할당 -> loadUrl
혹은

HTML 문자열로 웹뷰를 보여줌

매니페스트에 인터넷 퍼미션


* 기초 설정.

웹크롬클라이언트 웹뷰클라이언트 유용한기능

네비게이션

 
3. 웹뷰 오브잭트 관리

버전 api -> 크롬 버전, 유저에이전트 가져와도 됨

보안과 관련된 부분들 -> 웹브라우저를 만들것이 아니라면 특정 웹페이지를 로드할 것으로 특별히 신경쓸건 없음

웹뷰 랜더링 프로세스가 죽었을 경우 앱을 종료하지 않게 만드는 방법(그러나 웹뷰 오브잭트를 완전히 삭제 후 다시 만들어야함)


4. 4.4 웹뷰로 마이그레이션

4.4 로 변경되면서 나오는 여러가지 제약사항들. 업데이트를 하는게 아니라면 신경쓸 필요 없어보임


5. 다양한 화면 지원

장치 크기와 화면 사이즈에 대한 여러 메타 지정, 설정 지정

