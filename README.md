<h1>D + 1</h1>

# 시작하며

<br>

<strong>유튜브 강의를 보며 내가 알고있는 것 외의 것만 정리를 해놓는 것이긴 하지만 다른 사람들에게도 도움이 됐으면 좋겠다 .. !</strong>


이번 공부는 Boot + Jpa + Thymeleaf + MariaDB를 공부해보려고 한다.
공부의 자료 강의 출처는 [IT 늦공 김부장](https://www.youtube.com/@IT-sm6mq) 님의 유튜브 강의를 보면서 하는 것이다.

Jpa, Thymeleaf는 잘 모르는 기술이라 새롭게 공부해보려고 한다!


<br><br>
<hr>

# 프로젝트 생성

<br>

#### 프로젝트 설정
유료버전을 사용하는 사람은 인텔리제이에서 생성이 가능한데,
나는 인텔리제이 무료버전을 사용하고 있어서 spring initializer에서 프로젝트를 생성해주었다.


![](https://velog.velcdn.com/images/rkawksha/post/137cf9b8-ca27-4087-8315-7407394c9768/image.png)

프로젝트를 다 설정 하였으면 하단에 GENERATE를 클릭하면 zip파일로 다운로드가 된다.

나같은 경우 c 드라이브에 따로 폴더를 하나 생성하여 그 폴더에 압축을 풀어주었다.


<br><br>


#### 인텔리제이 import
상단에 file을 선택 후 Open File or Project를 선택하면 내 컴퓨터의 디렉토리 폴더들이 나오고 위에서 압축을 푼 폴더를 선택하여 해당 프로젝트를 선택해주면 된다.

![](https://velog.velcdn.com/images/rkawksha/post/a2e60154-ee4f-44b6-9f86-c4442ee7dc20/image.png)

<br>

그러면 이렇게 프로젝트가 생성이 된다 !
프로젝트의 구조가 조금 다르다면 
나는 몇개의 작업을 하여서 프로젝트 구조가 살짝 다를 수 있는 것이니 신경 안써도 된다 !

![](https://velog.velcdn.com/images/rkawksha/post/eb550500-f1cd-405c-8a86-9238788124a9/image.png)


<br><br>
<hr>

# 설정
<br>

#### build.gradle - thymeleaf
프로젝트를 생성할 때 Thymeleaf를 설정 하였는데 무슨일인지 dependencies에 들어가있지 않았다. 
그래서 [Maven Repository](https://mvnrepository.com/)에서 Thymeleaf를 검색 후 추가해주었다 !

이강의에서는 3.0의 버전을 사용하여 맞춰서 3.0의 최신 버전으로 추가해주었다.
![](https://velog.velcdn.com/images/rkawksha/post/134299a2-4f5d-4058-b6a5-939c09143bf6/image.png)

![](https://velog.velcdn.com/images/rkawksha/post/2b0eb706-357e-4159-abde-a9fd749d956e/image.png)


<br><br>

#### build.gradle - devtools
depencencies에 devtools를 추가를 하였는데 코드를 수정하여도 리로딩이 되지 않아서 설정을 바꿔주었는데 그 방법을 설명하겠다.

1. 상단에 File -> Setting 혹은 단축키 Ctrl + Alt + S를 눌러서 세팅 메뉴로 이동한다.

2. 설정창이 뜨면 Build, Exeution, Deployment -> Compiler -> Build project autiomaically를 체크해준다.
![](https://velog.velcdn.com/images/rkawksha/post/a32ae82d-289b-4de1-8712-a516fa6dbedc/image.png)
3. Advanced Settings -> Allow auto-make to start even if developed application is currently running을 체크한다.![](https://velog.velcdn.com/images/rkawksha/post/74aa5720-35ba-4f00-93b4-6d87af40b10e/image.png)

이렇게 설정을 해놓으면 될 것이다 ! ( 나는 됐다 .. ㅎ )


<br><br>

#### application.properties -> application.yml
이 강의에서는 properties를 사용하는 것이 하는 yml로 설정을 해주었다.
yml도 많이 사용을 한다고 한다 ! 그래서 해보는 것도 좋을듯 !

이 파일의 설정과 영상에 필요한 파일들은 해당 유튜브 강의를 방문해주면 다 있다 !

그래서 나의 설정만 보여주도록 하겠다.

```yml
--- # Start

--- # change port
server:
  port: 8085

--- # mariadb
spring:
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://localhost:3306/boot_study
    username: dongjun
    password: zxzx!203

--- #
spring:
  jpa:
    hibernate:
      ddl-auto: none #update
    properties:
      hibernate:
        show_sql : true
        format_sql: true
```

yml을 처음 사용해봤는데 작성하는 방식이 기존 properties보다 편한 것 같다
위에 보면 콜론(:)이 있는 것을 볼 수 있다.

mariadb 설정을 예로 든다면 
```
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://localhost:3306/
spring.datasource.username=
spring.datasource.password=
```

이렇게 설정을 해왔고 이 설정을 보면 4줄의 설정 코드가 다 spring.datasource 이후의 설정이란 것을 확인할 수 있다.
yml에서는 같은 부분을 계속 작성할 필요 없이 하위 설정이 같다면 필요한 설정만 작성하면 되어서 코드를 간편하게 작성하고 편리하게 볼 수 있다는 장점이 있다.

<br>

**그럼 작성은 어떻게 하는가?**
>기존 proeprties를 바탕으로 얘기를 하자면 " . " 대신에 앞에 콜론(:)을 붙여주고 공백을 한칸 둔 뒤에 정보를 작성해주면 된다 !

<br>

** --- # ...  주석 **
>설정에보면 ---#mariadb 처럼 주석같이 작성되어 있는 것을 볼 수 있다.
저 주석은 범위 또는 경계라고 보면 될것같다.
<br>
mariadb 주석에도 spring: 이 있고 그 밑에도 spring: 이 있는데, 만약 저런 주석이 없다면 중복으로 인한 에러가 난다.
<br>
(그럼.. 중복이니까 한 개만 적고 그 밑에 하위내용을 적어도 되나? - 이건 나의 개인적인 궁금증 따로 찾아봐야지..)
그래서 코드를 보기 편리함 + 에러 방지를 위해 적어주는 것이 좋을 것 같다.


<br><br>
<hr>

# static & templates

<br>

이제 html파일을 브라우저에 띄워보자 !

그 전에 디렉토리 구조를 보여주겠다.

![](https://velog.velcdn.com/images/rkawksha/post/c06a568b-0a14-4487-a18f-db9768a3d8f3/image.png)


#### static Directory
static에는 정적 파일들이 저장되는 곳으로, html파일이 있다면 url에 입력하여 바로 접속을 할 수 있다.
ex) localhost:8085/base/home.html

그래서 static 디렉토리에는 css, img, javaScript 등의 파일들이 저장이 된다.

<br>

#### templates
템플릿에 있는 파일은 컨트롤러를 통해 접근이 가능하다.
기본적으로 'resources:templates/' + {ViewName} + '.html' 이런식으로 제공이 되기때문에 ViewName만 적어주면 된다.

<br><br>

#### WebMvcConfigurer
보통 Controller에서 Mapping된 경로로 접속하여 View를 보는 방식이 있다.
하지만 이번 강의에서는 WebMvcConfigurer을 사용하여 확인해보았다.

<br>

**WebMvcConfigurer 이게 뭘까?**

```java
package com.example.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);

        registry.addViewController("/home/home").setViewName("/home/home");
        registry.addViewController("/member/list").setViewName("/member/member_list");
    }
}
```

>Spring MVC 설정을 사용자 정의할 수 있도록 하는 인터페이스이다.
그래서 이 인터페이스를 구현, 오버라이딩 하여 MVC설정을 사용자 정의할 수 있다.
<br>
public void addViewControllers(ViewControllerRegistry registry): 이 메서드는 ViewControllerRegistry를 사용하여 URL 경로와 뷰 이름을 매핑한다..
<br>
WebMvcConfigurer.super.addViewControllers(registry);: WebMvcConfigurer의 기본 구현을 호출한다. 필요하지 않지만, 나중에 다른 설정을 추가할 경우를 대비하여 호출한다.
<br>
registry.addViewController("/home/home").setViewName("/home/home");: URL 경로 /home/home에 대해 뷰 이름을 /home/home으로 설정한다. 즉, 사용자가 /home/home 경로로 요청하면 /home/home 뷰가 반환된다.
<br>
WebMvcConfigurer인터페이스를 구현한 MvcConfig 클래스는 Spring Boot 애플리케이션의 특정 URL 경로에 대한 뷰를 매핑한다. 예를 들어, 사용자가 /home/home 경로로 접근하면 /home/home 뷰가 반환되고, /member/list 경로로 접근하면 /member/member_list 뷰가 반환된다. 이러한 매핑을 통해 컨트롤러 없이도 특정 경로에 대해 뷰를 쉽게 설정할 수 있다.
<br>
출처 : https://chatgpt.com/ 활용


<br><br>
<hr>

# header & footer
<br>

각 html파일에 header 부분과 footer부분이 있을 것이다.
이 부분을 따로 디렉토리에 관리하며 가져올 때는 thymeleaf 템플릿 엔진의 th:include를 사용하여 header와 footer의 데이터를 가져와보려고 한다.

우선 html에서 thymeleaf를 사용하려면 thymeleaf를 추가해줘야한다.
```html
<html lang="en" xmlns:th="http://www.thymeleaf.org">
```

그리고 위의 프로젝트 구조를 보면 templates 안에 comm 디렉토리가 있고 여기에는 header.html과 footer.html이 존재하는데 footer의 코드의 경우
```html
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="bootstrap" viewBox="0 0 118 94">
        <title>Bootstrap</title>
        <path fill-rule="evenodd" clip-rule="evenodd" d="M24.509 0c-6.733 0-11.715 5.893-11.492 12.284.214 6.14-.064 14.092-2.066 20.577C8.943 39.365 5.547 43.485 0 44.014v5.972c5.547.529 8.943 4.649 10.951 11.153 2.002 6.485 2.28 14.437 2.066 20.577C12.794 88.106 17.776 94 24.51 94H93.5c6.733 0 11.714-5.893 11.491-12.284-.214-6.14.064-14.092 2.066-20.577 2.009-6.504 5.396-10.624 10.943-11.153v-5.972c-5.547-.529-8.934-4.649-10.943-11.153-2.002-6.484-2.28-14.437-2.066-20.577C105.214 5.894 100.233 0 93.5 0H24.508zM80 57.863C80 66.663 73.436 72 62.543 72H44a2 2 0 01-2-2V24a2 2 0 012-2h18.437c9.083 0 15.044 4.92 15.044 12.474 0 5.302-4.01 10.049-9.119 10.88v.277C75.317 46.394 80 51.21 80 57.863zM60.521 28.34H49.948v14.934h8.905c6.884 0 10.68-2.772 10.68-7.727 0-4.643-3.264-7.207-9.012-7.207zM49.948 49.2v16.458H60.91c7.167 0 10.964-2.876 10.964-8.281 0-5.406-3.903-8.178-11.425-8.178H49.948z"></path>
    </symbol>
    <symbol id="facebook" viewBox="0 0 16 16">
        <path d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951z"/>
    </symbol>
    <symbol id="instagram" viewBox="0 0 16 16">
        <path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z"/>
    </symbol>
    <symbol id="twitter" viewBox="0 0 16 16">
        <path d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334 0-.14 0-.282-.006-.422A6.685 6.685 0 0 0 16 3.542a6.658 6.658 0 0 1-1.889.518 3.301 3.301 0 0 0 1.447-1.817 6.533 6.533 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.325 9.325 0 0 1-6.767-3.429 3.289 3.289 0 0 0 1.018 4.382A3.323 3.323 0 0 1 .64 6.575v.045a3.288 3.288 0 0 0 2.632 3.218 3.203 3.203 0 0 1-.865.115 3.23 3.23 0 0 1-.614-.057 3.283 3.283 0 0 0 3.067 2.277A6.588 6.588 0 0 1 .78 13.58a6.32 6.32 0 0 1-.78-.045A9.344 9.344 0 0 0 5.026 15z"/>
    </symbol>
</svg>
<div class="container">
    <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
        <div class="col-md-4 d-flex align-items-center">
            <a href="/" class="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1">
                <svg class="bi" width="30" height="24"><use xlink:href="#bootstrap"/></svg>
            </a>
            <span class="text-muted">&copy; 2021 Company, Inc - footer</span>
        </div>

        <ul class="nav col-md-4 justify-content-end list-unstyled d-flex">
            <li class="ms-3"><a class="text-muted" href="#"><svg class="bi" width="24" height="24"><use xlink:href="#twitter"/></svg></a></li>
            <li class="ms-3"><a class="text-muted" href="#"><svg class="bi" width="24" height="24"><use xlink:href="#instagram"/></svg></a></li>
            <li class="ms-3"><a class="text-muted" href="#"><svg class="bi" width="24" height="24"><use xlink:href="#facebook"/></svg></a></li>
        </ul>
    </footer>
</div>
```

이런식으로 필요한 부분만 코드로 적혀져 있다 이 것을 home.html에 가져와보도록 할 것이다.

<br>


#### th:include

footer를 입력할 부분에 밑에와 같이 코드를 작성해주면 해당 데이터를 가져올 수 있다.
header와 footer 동일하게 작성하면 된다 !
```html
<div th:include="~{/comm/footer}"></div>
```

include 말고도 다른 방법도 존재한다.
[여기](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#difference-between-thinsert-and-threplace-and-thinclude) 를 방문하여 확인하여 원하는 방법으로 사용하면 될 것 같다 !


<br><br>
<hr>


# 마치며

<br>

면접을 보고 피드백 받을 수 있냐는 질문을 하였다.
내가 부족한 부분과 있었으면 좋겠다 하는 기술에 대해 여쭤보았다.
그래서 들은 대답은 JPA와 React였다.
사실 생각으로는 공부 해봐야지 해봐야지 하며 그냥 기존 기술로 프로젝트만 진행하였는데,
기존에 알고있던 기술스택으로는 이미 프로젝트도 만들어 봤겠다 그냥 새로운 기술도 공부를 해보자는 의지가 불타오르는 계기가 되었다. 

JPA에 대해 아직은 초입단계라 많은 정보가 없겠지만 JPA와 Thymeleaf를 꾸준히 공부하며 제대로 익혀봐야겠다.


혹시 내가 정리한 것이 틀린 것이 있다면 바로 피드백 해주시면 감사하겠습니다 ! 

<br>
<span style="font-size:30px; color:blue">그럼 안녕 ~ 🖐🖐</span>
