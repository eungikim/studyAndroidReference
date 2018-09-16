#액티비티 소개
-------------
###목차 
----------------
1. 액티비티의 개념
2. 매니페스트 구성
	- 선언활동
	- 인텐트 필터 선언
	- 사용 권한 선언
3. 액티비티 생명주기

-----------------

> [Activity](https://developer.android.com/reference/android/app/Activity) class는 안드로이드 앱을 구성하는 중요한 요소임.
> 기존 main()메소드로 시작되는 프로그래밍 패러다임과는 달리 안드로이드 시스템의 Activity는 생명주기(life cycle)의 특정 단계에 해당하는 콜백 메소드를 호출 -> 인스턴스에서 코드를 시작함. 참조 [응용프로그램 아키텍처 가이드](
https://developer.android.com/jetpack/docs/guide "응용프로그램 아키텍처 가이드")

----------

###1. 액티비티의 개념
모바일앱 환경은 테스트톱 앱과 달리 사용자와 상호작용이 항상 같은 장소에서 시작되지 않음.

한 응용프로그램이 다른 응용프로그램을 호출 시 응용프로그램만 호출하는 것이 아니라,
//원자(atomic) 전체//로 응용프로그램의 '활동'을 호출함.
액티비티는 앱과 사용자 간의 상호작용을 위한 진입점(접점) 역할을 함.
MainActivity extends Activity와 같이 클래스의 하위 클래스로 구현(implement)함.
	
액티비티는 UI 를 그리는 창을 제공함.
일반적으로 하나의 액티비티는 하나의 화면을 구현함. -> 독립성이야기인듯?
	
앱실행시 표시되는 첫번째 화면(기본 MainActivity)인
기본액티비티(manifest에서 다른 액티비티로 변경 할 수 있음)로 지정할 수 있음.

각 액티비는 다른 액티비티에만 느슨하게 결합됨(독립성이야기?)
앱의 활동간에 최소한의 의존성을 가짐.

앱에서 액티비티를 사용하려면 manifest에 해당 액티비티에 대한 정보를 등록해야함.
생명주기를 적절하게 관리해야함(이부분이 나올것임).

-------------------

###2. manifest의 구성
####액티비티 선언
앱에서 액티비티를 사용하려면 manifest에서 액티비티 및 특정 attributes를 선언해야함.

    <manifest...>
		<application...>
			<activity android:name=".ExampleActivity"/>
			...
		</application...>
	</manifest>
	
[application](https://developer.android.com/guide/topics/manifest/application-element, "")의 하위요소로 들어감.
유일한 필수 속성은 액티비티 class의 이름을 지정하는 [android:name](https://developer.android.com/guide/topics/manifest/activity-element#nm)임.
*lable, icon 또는 UI theme와 같은 활동 특성을 정의하는 속성을 추가할 수도 있음*
-> 참조 [activity](https://developer.android.com/guide/topics/manifest/activity-element)내 요소 참조

>참고 : 앱을 게시 한 후에는 활동 이름을 변경하면 안됩니다.
>그렇게하면 앱 바로 가기 같은 일부 기능이 중단 될 수 있습니다. 
게시 이후 변경 사항에 대한 자세한 내용은 [변경할 수없는 사항](https://android-developers.googleblog.com/2011/06/things-that-cannot-change.html)을 참조 하십시오 .

####intent filter 선언
//설명이 모호하여 manifest부분에서 intent 선언을 하여 제어할 수 있음 -> 참고필요
//[intent 및 intent filter](https://developer.android.com/guide/components/intents-filters)

명시적 요청뿐만 아니라 암시적 요청을 기반으로 활동을 시작하는 기능을 제공한다.

	명시적요청 : Intent(Context, Class) Class를 명시적으로 시작
	암시적요청 : 작업을 지정하여 기기에서 해당 작업을 수행할 수 있는 모든 앱을 호출 할 수 있도록함

		주의 : 개발자가 startActivity()로 전송한 암시적 인텐트를 
		처리할 앱이 사용자에게 전혀 표시되지 않을 수도 있습니다. 
		이런 일이 발생하면, 호출이 실패하고 앱이 작동 중단됩니다. 
		어느 액티비티든 이 인텐트를 수신하도록 확실히 하려면, 
		Intent 객체의 resolveActivity()를 호출합니다. 
		결과가 null이 아닌 경우, 인텐트를 처리할 수 있는 앱이 
		최소한 하나는 있다는 뜻이며 startActivity()를 호출해도 안전합니다. 
		결과가 null이면, 해당 인텐트를 사용해서는 안 되며 가능한 경우 
		해당 인텐트를 발생시키는 기능을 비활성화해야 합니다.

<activity>요소에 <intent-filter>특성을 선언하여 이 기능을 활용할 수 있음
<action>요소, 선택적으로 <category>요소 및 <data>요소가 포함됨
	
	<activity android:name=".ExampleActivity" android:icon="@drawable/app_icon">
	    <intent-filter>
	        <action android:name="android.intent.action.SEND" />
	        <category android:name="android.intent.category.DEFAULT" />
	        <data android:mimeType="text/plain" />
	    </intent-filter>
	</activity>
	
    // Create the text message with a string
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.setType("text/plain");
    sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
    // Start the activity
    startActivity(sendIntent);

####사용 권한 설정 선언

manifest의 태그를 사용하여 특정 활동을 시작할 수 있는 앱을 제어할 수 있음.
상위 액티비티는 두 액티비티가 동일한 권한을 가지고 있지 않으면 하위 액티비티 실행할 수 없음.
예를 들어 '소셜앱'이라는 가상의 앱을 사용하여 '소셜미디어'에 게시물을 공유하려는 경우
'소셜'앱 자체에서 호출해야하는 앱의 권한을 정의해야함.
	
	<manifest>
	<activity android:name="...."
	   android:permission=”com.google.socialapp.permission.SHARE_POST”/>

그런 다음 '소셜액'을 호출하려면 앱이 '소셜앱'의 manifest에 설정된 권한과 일치해야함.

	<manifest>
	   <uses-permission android:name="com.google.socialapp.permission.SHARE_POST" />
	</manifest>

참조 [보안 및 사용권한](https://developer.android.com/training/articles/security-tips) -> 안드로이드 5.0부터는 보안이슈로 인해 사용자에게 직접권한을 얻어야하는 부분 생김.

---------------------
###3. 활동 라이프 사이클 관리

액티비티는 여러 주(number of states//상태?)를 통과함. 일련의 콜백을 사용해 상태간의 전환을 처리.

####[onCreate()](https://developer.android.com/reference/android/app/Activity#onCreate(android.os.Bundle))
시스템이 활동을 생성할 때 발생하는 콜백을 구현해야함.구현시 활동의 필수 구성 요소를 초기화해야함. 앱에서 보기를 만들고 여기에 목록 데이터를 바인딩해야함. 중요한 점은 setContentView() 액티비티의 사용자 인터페이스에 대한 레이아웃을 정의하기위해 호출해야함.

onCreate()가 끝낼때, 다음 콜백은 항상 onStart().

####[onStart()](https://developer.android.com/reference/android/app/Activity#onStart())
마찬가지로, onCreate()가 끝날때, 액티비티가 시작상태에 진입하고, 액티비티가 사용자에게 표시됨.
이 콜백에는 전면에 나와서 상호작용을 하기 위한 활동의 최종 준비에 해당하는 내용이 포함되어 있습니다.

####[onResume()](https://developer.android.com/reference/android/app/Activity.html#onResume())
시스템은 액티비티가 사용자와 상호작용하기 직전에 이 콜백을 호출함. 이 시점에서 액티비티는 액티비티스택의 맨 위에 있으며 모든 사용자 입력을 캡처함. 대부분의 앱의 핵심 기능이 이 onResume()메소드에서 구현됨.

onPause() 콜백은 항상 다음 onResume().

####[onPause()](https://developer.android.com/reference/android/app/Activity#onPause())
onPause()는 액티비티가 포커스를 잃고 일시중지 상태가되면 시스템이 호출합니다. 이 상태는 사용자가 뒤로 또는 최근 버튼을 누를 때 발생하는 것을 생각하면 됨. 시스템이 액티비티를 위해 onPause()를 호출하는 경우 기술적으로 사용자의 액티비티가 부분적으로 표시되지만, 대부분은 사용자가 액티비티를 종료함을 나타내며 액티비티는 중지 또는 재개됨 상태로 곧 진입함.

사용자가 UI를 업데이트 할 것으로 예상하는 경우 Paused상태의 활동이 UI를 계속 업데이트할 수 있음. 예로 네비게이션 맵화면 또는 재생중 미디어플에이어를 나타내는 것을 포함. 액티비티가 포커스를 잃더라도 사용자는 자신의 UI가 계속 업데이트 될 것으로 기대함(앱종료가아닌 상주(?))

애플리케이션 또는 사용자 데이터를 저장하거나, 네트워크 통화를 하거나, 데이터베이스 트랜잭션을 실행하는 데 onPause()를 사용하면 안됨. 자세한 내용은 [작업상태 저장 및 복원](https://developer.android.com/guide/components/activities/activity-lifecycle#saras)을 참조

onPause()실행이 끝나면 다음 콜백은 활동이 일시 중지된 상태가 된 후 발생하는 상황에 따라 onStop()또는 onResume()임.

####[onStop](https://developer.android.com/reference/android/app/Activity.html#onStop())
액티비티가 더 이상 사용자에게 표시되지 않을때 호출됨. 액티비티가 파괴되거나, 새 액티비가 시작되고 있거나, 기존 액티비티가 재개된 상태로 전환되어 중지된 액티비티을 다루고 있기 때문에 발생할 수 있습니다. 이러한 모든 경우 중지된 활동은 더 이상 표시되지 않습니다.

시스템이 호출하는 다음 콜백 onRestart()는 액티비티가 사용자와 상호작용하기 위해 되돌아오는 onDestroy() 경우거나 활동이 완전히 종료된 경우임.

####[onRestart](https://developer.android.com/reference/android/app/Activity#onRestart())
액티비티가 삭제되기 전에 시스템이 콜백을 호출

액티비티가 받는 최종 콜백으로 onDestroy()는 일반적으로 액티비티 또는 액티비티를 토함하는 프로세스가 파기 될 때 활동의 모든 자원이 해제되도록 보장하기 위해 구현됨.

![](https://developer.android.com/guide/components/images/activity_lifecycle.png)