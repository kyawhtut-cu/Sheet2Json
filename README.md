
# Sheet2Json

Application backend အသုံးမပြုပဲ Google sheet မှ Data များကို Api အနေဖြင့် အသုံးပြုရန်အတွက် Parser ဖြစ်ပါသည်။

Google sheet မှ data များကို Json အနေဖြင့် သုံးလိုသူများအတွက် အလွယ်တကူ အသုံးပြုရပါသည်။

အခု Library သည် [GSX2JSON - Google Spreadsheet to JSON API service.](http://gsx2json.com/) ကို အခြေခံပြီး Android library ရေးသားထားခြင်းဖြစ်ပါသည်။

ထို Site သည် Open source ပေးထားပြီး [heroku](https://www.heroku.com/) တွင် Host လုပ်ထားခြင်းဖြစ်သည့် အတွက် Request limitation, bandwidth limitation ရှိသည့် အတွက် Sheet api ကို Client တွင် တိုက်ရိုက်ခေါ်ပြီး Json ပြောင်းထားခြင်းဖြစ်ပါသည်။

ယခု Version တွင် Response သည် Json ဖြစ်ပြီး။​ Response ရမှသာ Gson converter ဖြစ်ဖြစ် ကြိုက်နှစ်သက်ရာ Converter တစ်ခုခုကို အသုံးပြုပြီး Object ပြန်ပြောင်းရမှာဖြစ်ပါတယ်။

How to add to your project
--------------

1. Add jitpack.io to your root build.gradle file:

     ```groovy
       allprojects {
         repositories {
           ...
           maven { url 'https://jitpack.io' }
         }
       }

2. Add library to your app build.gradle file then sync

   Release version - [![Download](https://raw.githubusercontent.com/kyawhtut-cu/Sheet2Json/master/screenshoot/download.svg?sanitize=true)](https://github.com/kyawhtut-cu/Sheet2Json/releases/)

   ```groovy
   dependencies {
      ...
      implementation 'com.github.kyawhtut-cu:Sheet2Json:<version-release>'
   }
   ```

Usage
--------------

    /* *
       *
       * Sheet2Json initialized in Activity onCreate() only one time initialized.
       *
       * */
       Sheet2Json.init()

       /* *
       *
       * Request api
       * sheetID = "1bA0N-e5n-kLnMGE3isMMOv6lAOKDRBELF6YWJBxxxxx" // you can get from doc sheet url
       * sheetNumber = 1 // this is optional you want to read sheet number, default is one
       * query = "" // this is optional you want to filter, default is empty
       *
       * */
       Sheet2Json.get(sheetID, sheetNumber, query) // return is Single<String>

       /* *
       *
       * Complete code
       *
       * */
       Sheet2Json.get("1bA0N-e5n-kLnMGE3isMMOv6lAOKDRBELF6YWJBlZmbQ")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val data: List<People> = Gson().fromJson<List<People>>(
                        it,
                        object : TypeToken<List<People>>() {}.type
                    )
                },
                {
                    it.printStackTrace()
                }
            )
            .isDisposed

       /* *
       *
       * Add internet permission in AndroidManifest.xml
       *
       * */
       <uses-permission android:name="android.permission.INTERNET" />

Screenshoot
--------
  <img alt="English Unicdoe Choose" src="https://raw.githubusercontent.com/kyawhtut-cu/Sheet2Json/master/screenshoot/mobile-screen-shoot.jpg" width="250"/>

   <img alt="English Zawgyi Choose" src="https://raw.githubusercontent.com/kyawhtut-cu/Sheet2Json/master/screenshoot/sheet-screen-shoot.png" width="250"/>

Reference
--------

GSX2JSON - Google Spreadsheet to JSON API service.(https://github.com/55sketch/gsx2json)

License
--------

    Copyright 2019 kyawhtut-cu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
