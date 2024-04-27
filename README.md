# Тестовое задание для отбора на Летнюю ИТ-школу КРОК по разработке

## Условие задания
Один развивающийся и перспективный маркетплейс активно растет в настоящее время. Текущая команда разработки вовсю занята тем, что развивает ядро системы. Помимо этого, перед CTO маркетплейса стоит задача — разработать подсистему аналитики, которая на основе накопленных данных формировала бы разнообразные отчеты и статистику.

Вы — компания подрядчик, с которой маркетплейс заключил рамочный договор на выполнение работ по разработке этой подсистемы. В рамках первого этапа вы условились провести работы по прототипированию и определению целевого технологического стека и общих подходов к разработке.

На одном из совещаний с Заказчиком вы определили задачу, на которой будете выполнять работы по прототипированию. В качестве такой задачи была выбрана разработка отчета о периодах наибольших трат со стороны пользователей.

Аналитики со стороны маркетплейса предоставили небольшой срез массива данных (файл format.json) о покупках пользователей, на примере которого вы смогли бы ознакомиться с форматом входных данных. Каждая запись данного среза содержит следующую информацию:
- Идентификатор пользователя;
- Дата и время оформления заказа;
- Статус заказа;
- Сумма заказа.

В пояснительной записке к массиву данных была уточняющая информация относительно статусов заказов:
- COMPLETED (Завершенный заказ);
- CANCELED (Отмененный заказ);
- CREATED (Созданный заказ, еще не оплаченный);
- DELIVERY (Созданный и оплаченный заказ, который доставляется).

Необходимо разработать отчет, вычисляющий по полученному массиву данных месяц, когда пользователи тратили больше всего. Если максимальная сумма пользовательских трат была в более, чем одном месяце, отчет должен показывать все такие месяцы. В отчете должны учитываться только завершенные заказы.

Требования к реализации:
1. Реализация должна содержать, как минимум, одну процедуру (функцию/метод), отвечающую за формирование отчета, и должна быть описана в readme.md в соответствии с чек-листом;
2. В качестве входных данных программа использует json-файл (input.json), соответствующий структуре, описанной в условиях задания;
3. Процедура (функция/метод) формирования отчета должна возвращать строку в формате json следующего формата:
   - {«months»: [«march»]} 
   - {«months»: [«march», «december»]}
4. Найденный в соответствии с условием задачи месяц должен выводиться на английском языке в нижнем регистре. Если месяцев несколько, то на вывод они все подаются на английском языке в нижнем регистре в порядке их следования в течение года.

## Автор решения
Ерошенко Артём Игоревич
## Описание реализации
1) Создаётся класс Заказа Order. Его атрибуты соответствуют таковым в json-файле. Они помечены анотациями @JsonProperty, которые устанавливают между ними соотвествия. Геттеры и сеттеры заданы соответсвующими аннотациями из Lombok.
2) Создаётся класс Месяцев для Отчёта MonthReported. Его атрибуты - список месяцев. Конструктор задан соответсвующей аннотацией из Lombok. Этот класс понадобится для вывода в формате json.
3) Класс Отчёта Reporter содержит метод формирования отчёта reportBiggestMonth. Она принимает объект класса ObjectMapper (для работы с json) и список объектов класса Order. В методе есть словарь, хранящий месяц как ключ и сумму заказов за него как значение. Он заполняется через stream: filter выбирает только завершённые заказы (проверяет, является ли статус заказа COMPLETED), collect собирает элементы в коллекцию, группируя месяцы и складывая суммы заказов. Далее, среди значений словаря вычисляется наибольщая сумма. После чего, формируется список месяцев, чья сумма равняется максимальной. Создаём экземпляр класса, содержащего список месяцев и с помощью objectMapper метод возвращает String в формате JSON
4) В классе Main создаётся экземпляр ObjectMapper, читает json из файла, преобразуя его в список объектиов класса Order. Далее экземпляр ObjectMapper и список заказов передаётся методу reportBiggestMonth, а возвращаемое значение выводится
## Инструкция по сборке и запуску решения
Достаточно запустить метод main
