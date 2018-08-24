
안드로이드 래퍼런스 스터디
=

안드로이드 래퍼런스를 공부하기 위해 결성된 스터디입니다. 부천~서울에서 매주 일요일에 모임을 가지고 있습니다.

개인마다 공부하고 싶은 부분을 정해 스터디에 공고 한 뒤 그 부분을 공부하고 요약하여 정리 문서 작성, 토이(샘플) 프로잭트를 만들어 격주마다 스터디원들에게 발표를 합니다.

현제 인원을 더 받고 있으므로 znfks8819@naver.com 으로 간단한 소개와 연락처를 적어 메일주세요.

* 현제 인원: 4명
* 첫번째 발표: YTY, KTY
* 두번째 발표: SHC, KEG


# Github 작업 방법
git bash 를 사용한다는 전체하에 작성하였습니다.
>다운로드 : https://gitforwindows.org/

## 1. 클론 받기

레포지토리로 이동하여 좌측 상단에 있는 초록색 Clone or download 버튼을 클릭합니다.

나와있는 url 을 통해 clone 을 받거나 집으로 받아 압축을 풀어주세요.

    $git clone https://github.com/user-name/repository-name.git

git bash 에서 해당 디렉토리로 이동 바랍니다.

![클론 버튼](/README.img/imgCloneBtn.PNG)


## 2. 브런치 관리

브런치 관리는 상용 브런치인 master, 개발 브런치인 dev, 개인 브런치인 dev-myname 을 사용합니다.

git bash 에서 브런치를 생성해주세요.

    $git branch dev-myname

브런치로 이동합니다.

    $git checkout dev-myname

한번에 하는 방법

    $git checkout -b dev-myname

![브런치 도식도](/README.img/imgBranchModel.jpg)

* ### __항상 브런치를 자기 브런치로 유지하는것을 잊지 말아주세요.__


## 3. 작업

해당 브런치에서 각자 자기가 공고한 공부할 부분의 디렉토리를 생성합니다.

>\studyAndroidReference\docs\guides\activities\fragment\overveiw\

해당 디렉토리에서 README.md 를 작성하여 공부 범위의 요약 혹은 번역과 요약을 하시면 됩니다.

그 다음 샘플 프로젝트는 위치에 알맞게 fragment\overview 혹은 몇가지 소제목을 아우른다면 fragment\ 안에 만드시면 됩니다. 프로젝트를 자기 workSpace 에 만든 뒤 위의 path 안에 넣는 방법은 검색해보니 없다고 나오네요! (2016 답변)

.gitignore 는 예제 파일로 바꾸겠습니다.
>https://github.com/github/gitignore/blob/master/Android.gitignore

git bash 에서 해당 프로젝트를 리모트 리포지토리로 올리겠습니다. 각각의 명령어에 대해 바뀌는 파일의 상태는 따로 공부해주세요.

    $git add * 
    $git commit -m "sample project1 first commit" 

그 후 코드를 수정하면서 변경사항이 생길 때 마다 위와 같은 명령어로 local branch 에 기록하시면 됩니다. 안드로이드 스튜디오에서 git 을 추가하여 사용도 가능합니다. 그러면 untracked 파일은 빨간색으로 modify 된 폴더는 초록색으로 나옵니다. 그 방법은 따로 적지 않겠습니다.


## 4. 통합

각자 dev-myname 브런치에서 작업을 하셨을겁니다. 그러면 이 브런치에서 수정한 것을 remote repository 에 올리겠습니다.

    $git push -u origin dev-myname

ssh 연동을 안하셨으면 올리실 때 마다 github username 과 password 를 적어야합니다.

-u 옵션을 사용한 다음에는 다음에 git push 만 적으셔도 자동으로 branch 가 기억됩니다.

dev-myname remote repository 에 올리셨다면 dev 브런치로 통합을 하셔야합니다. 브라우저에서 repository 로 가서 상단의 pull requests 메뉴를 클릭해주세요.

![풀리퀘스트 버튼](/README.img/imgPullRequestMenu.PNG)

그러면 최근 풀리퀘스트의 목록이 나옵니다. 옆의 초록색 Compare & pull request 버튼을 눌러주세요.

![풀리퀘스트 목록](/README.img/imgPullRequestList.PNG)

그 다음 풀리퀘스트를 어느 브런치에서 어느 브런치로 할 것인지 선택하는 곳이 나옵니다. 자기 브런치에서 dev 브런치로 선택해주세요. 기본은 master 브런치일 것입니다.

![풀리퀘스트 브런치 선택](/README.img/imgPullRequestBranchCheck.PNG)

아래를 보시면 커밋 로그들과 변경사항이 보입니다. 타이틀과 코멘트를 넣고 Create pull request 버튼을 눌러주시면 됩니다.

그러면 풀리퀘스트가 생성이 되고 풀리퀘스트 메뉴에 1이 나타납니다. 풀리퀘스트를 선택 후 [This branch has no conflicts with the base branch] 문구가 나오면 통합하는데 아무 이상 없는것이니 아래 머지 버튼을 누르시면 됩니다.

사실 풀리퀘스트를 자기가 날리고 자기가 그것을 승인하는것은 번거로운 작업입니다. 현제는 레포지토리에 스터디원 분들을 협업자로 적어서 모든 권한을 가지기 때문에 직접 마스터 브런치에서 작업을 할 수 도 있었는데요. 추후에 조직을 구성해서 권한까지 조절할 생각이기 떄문에 이런 기존의 협업 프로세스를 사용해주셨으면 합니다.


## 5. 다시 작업

작업을 개시하기 전에 최신 작업상황을 받고 싶으시다면 pull 명령어를 사용하시면 됩니다. 이는 fetch 명렁어와 merge 명령어가 합쳐진 것입니다.

자기 브런치에서 최신 사항을 받습니다. 남이 자기의 브런치를 수정했을 경우에 할 수 있습니다.

    $git checkout dev-myname
    $git pull

통합 브런치에서 최신 사항을 받습니다. 다른 사람들의 작업물까지 받기 위해 사용합니다.

    $git checkout dev-myname
    $git pull origin master

* ### __항상 브런치를 자기 브런치로 유지하는것을 잊지 말아주세요.__


## 6. 에러 해결

추후 적음