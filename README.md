[![HitCount](http://hits.dwyl.com/saechimdaeki/Cyphers_INFO.svg)](http://hits.dwyl.com/saechimdaeki/Cyphers_INFO) ![license](https://img.shields.io/github/license/saechimdaeki/Cyphers_INFO) ![star](https://img.shields.io/github/stars/saechimdaeki/Cyphers_INFO) ![issue](https://img.shields.io/github/issues/saechimdaeki/Cyphers_INFO)
# Cyphers_Info(사이퍼즈 정보통신기)
#### 온라인게임 사이퍼즈의 전적검색 및 정보,홍보용 어플리케이션입니다.(Development Start Date: 2020-05-19) 

### Retrofit2+Databinding+MVVM(MVVM NOT YET..)
## [사이퍼즈오픈api](https://developers.neople.co.kr/contents/apiDocs/cyphers) 에서 RestApi를 수신합니다.
#### 사이퍼즈 게임을 즐겨하는 유저들을 위한 어플리케이션입니다. 

    주요 기능으로는 유저의 전적검색,랭킹, 아이템조회, 홈페이지 소식, 포지션특성조회, 투신전, 보이스박스,
    캐릭터 팁, 공략, 스토리(뷰페이저에 bookflip을 달아 책처럼 읽을수있습니다)입니다.

    (오직 사이퍼즈를 즐겨하거나 관심있는 유저를 위한 특정타겟팅 어플리케이션입니다.) 

[다운받으러가기](https://play.google.com/store/apps/details?id=nexon.cyphers.app)
### 6월 25일 사이퍼즈 공식홈페이지에서 오늘의 사이퍼즈로 선정되었습니다
[보러가기](http://cyphers.nexon.com/cyphers/article/bestart/topics/28012644)

### 2020 08 12 
- 신규캐릭터 및 jsoup파싱 태그 변경.
- ~~이슈발생.. (wifi환경에서 jsoup을 반복적으로 이용할경우 홈페이지에서 해당기능을막음)~~ 현재 해결되어 원활히 동작중 
- 현재 공식소식, 캐릭터정보/보이스박스, 세계관은 wifi환경에서는 anr문제를 일으키며 3G,4G,5G의 환경에서는 원활히 작동합니다.

#### 신규캐릭터도 정상적으로 정보가 제공됩니다
![플레이스토어1](https://user-images.githubusercontent.com/40031858/84632569-0e340980-af2a-11ea-89f9-74f289ebeec0.jpg)


![플레이스토어2](https://user-images.githubusercontent.com/40031858/84632665-3459a980-af2a-11ea-9f2d-58d1a378d0b8.png)

![플레이스토어3](https://user-images.githubusercontent.com/40031858/84632694-3f143e80-af2a-11ea-9072-038bafd7e65f.png)

![플레이스토어4](https://user-images.githubusercontent.com/40031858/84632724-489da680-af2a-11ea-9524-7fe04ed8b0a2.png)

## 📝 License
This project is released under the MIT license.
See [LICENSE](./LICENSE) for details.

```
MIT License

Copyright (c) 2020 김준성

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```
---

##   📃 Libraries used

*   [AndroidX](https://developer.android.com/jetpack/androidx/) - Previously known as 'Android support Library'
*   [Glide](https://github.com/bumptech/glide) - for loading and caching images 
*   [Retrofit 2](https://github.com/square/retrofit) - Type-safe HTTP client for Android and Java by Square, Inc. 
*   [Gson](https://github.com/google/gson) - for serialization/deserialization Java Objects into JSON and back
*   [DataBinding](https://developer.android.com/topic/libraries/data-binding/)
*   [CircleImageView](https://github.com/hdodenhof/CircleImageView)
*   [CircleIndicator](https://github.com/ongakuer/CircleIndicator)
*   [Jsoup](https://jsoup.org/)
*   [MaterialDialog](https://github.com/PatilShreyas/MaterialDialog-Android)
*   [OverScroll](https://github.com/EverythingMe/overscroll-decor)
*   [FlipViewpager](https://github.com/Yalantis/FlipViewPager.Draco)
*   [Lottie](https://github.com/airbnb/lottie-android)
* Etc...
