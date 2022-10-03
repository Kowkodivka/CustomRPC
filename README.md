# CustomRPC
Простая программа для кастомного RPC в Discord
# Использование
## Как пользоваться программой
### Работает только на PC версии Discord!
Скачайте `.jar` файл из релизов репозитория. Убедитесь, что у вас стоит последняя версия java/jre. Запустите файл файл командой `java -jar rpc.jar` и заполните необходимую информацию в сгенерированном файле. __Важно:__ вам необходимо создать новое приложение на discord developer portal, где можно будет ставить иконки для статуса и где будет находиться id вашего приложения.
<br>
Пример конфигурационного файла:
```json
{
  "discord-id": 0,
  "large-image-key": "image-big",
  "large-image-text": "This is big image!",
  "small-image-key": "image-small",
  "small-image-text": "This is small image!",
  "words": ["Word", "Another word"],
  "details": ["Hello there", "What are you doing here?"],
  "button": "Github",
  "link": "https://github.com",
  "interval": 2.0
}
```

## Компиляция

Gradle может потребоваться до нескольких минут для загрузки файлов. <br>
После сборки выходной jar-файл должен находиться в каталоге `/build/libs/DarkdustryPlugin.jar`.

Сначала убедитесь, что у вас установлен JDK 16-17. Откройте терминал в каталоге проекта и выполните следующие команды:

### Windows

_Компиляция:_ `gradlew jar`  

### Linux/Mac OS

_Компиляция:_ `./gradlew jar`  

### Устранение неполадок

#### Permission Denied

Если терминал выдает `Permission denied` или `Command not found` на Mac/Linux, выполните `chmod +x ./gradlew` перед запуском `./gradlew`. *Это одноразовая процедура.*
