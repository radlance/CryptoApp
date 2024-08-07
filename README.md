# Crypto App

![app](https://camo.githubusercontent.com/323ef51f2f921bd312c333ac04eafb8deb8e1322c43bcae4a3d1e608c20a00a4/68747470733a2f2f696d672e736869656c64732e696f2f7374617469632f76313f7374796c653d666f722d7468652d6261646765266d6573736167653d416e64726f696426636f6c6f723d333441383533266c6f676f3d416e64726f6964266c6f676f436f6c6f723d464646464646266c6162656c3d)


Android-приложение, отображающее список криптовалют.
Приложение создано с использованием
Retrofit, Hilt, Coroutines, MVVM, и Clean Architecture.
---

## Функции

+ Предоставление актуального списка криптовалют в удобном формате.



---

## Архитектура

Приложение создано с соблюдением чистой архитектуры,
код разделен на три отдельных уровня:
представление, домен и данные.

+ **Уровень представления**:
  уровень отвечает за пользовательский интерфейс
  и взаимодействие с пользователем.
  Он использует шаблон MVVM (Model-View-ViewModel) для отделения логики
  пользовательского интерфейса от бизнес-логики.


+ **Уровень домена**: уровень содержит бизнес-логику приложения.
Он определяет варианты использования и взаимодействие с уровнем данных.


+ **Уровень данных**: уровень отвечает за выборку и хранение данных.
  В нем происходят все сетевые запросы.

При вынесении перечисленных уровней в отдельные модули,
предоставление и внедрение зависимостей осуществляется
в модуле приложения.

---

## Используемые технологии
+ **Kotlin (v 1.9.0)** — официально поддерживаемый Google язык
  разработки приложений для Android.

+ **Android Studio (v 2023.2.1)** — официальная интегрированная среда 
  разработки (IDE) для разработки приложений Android.


+ **Retrofit (v 2.10.0)** — HTTP-клиент для Android, 
  используемый для выполнения сетевых запросов.


+ **Hilt (v 2.50)** — библиотека от Google, 
используемая для предоставления и внедрения зависимостей.


+ **Coroutines** — компоненты программы, 
  которые позволяют выполнить асинхронные вычисления.


+ **MVVM** — архитектурный шаблон, 
  используемый для отделения логики пользовательского интерфейса от
  бизнес-логики.

![android studio](https://camo.githubusercontent.com/2d397c08eedc8787ef2a85a6a4b391f62d5ef4d89c527e49bc9f3a0b8c54136f/68747470733a2f2f696d672e736869656c64732e696f2f7374617469632f76313f7374796c653d666f722d7468652d6261646765266d6573736167653d416e64726f69642b53747564696f26636f6c6f723d323232323232266c6f676f3d416e64726f69642b53747564696f266c6f676f436f6c6f723d334444433834266c6162656c3d)
![kotlin](https://camo.githubusercontent.com/d3d1086af5c2cc9b242b19407152596a33d4ee77f4c2c76f561ba14a2ee8abe0/68747470733a2f2f696d672e736869656c64732e696f2f7374617469632f76313f7374796c653d666f722d7468652d6261646765266d6573736167653d4b6f746c696e26636f6c6f723d374635324646266c6f676f3d4b6f746c696e266c6f676f436f6c6f723d464646464646266c6162656c3d)
---

## Экраны
1. **Список криптовалют**: на экране отображается список криптовалют с их значком, 
  названием, ценой и временем последнего обновления.
  При нажатии на криптовалюту открывается подробный вид.

   
  

2. **Экран детальной информации**: на этом экране отображается 
  более подробная информация о криптовалюте,
  включая минимальную и максимальную цену,
  а также информацию о последней сделке.


   ![Список криптовалют](img_1.png)
   ![img_2.png](img_2.png)

 
---

## Установка
Для установки приложения загрузите **[apk-файл](https://github.com/radlance/CryptoApp/raw/master/app/release/app-release.apk)**
и откройте его на своем устройстве Android.
Альтернативно вы можете
клонировать репозиторий и создать приложение с помощью Android Studio.
```
git clone git@github.com:radlance/CryptoApp.git
```
