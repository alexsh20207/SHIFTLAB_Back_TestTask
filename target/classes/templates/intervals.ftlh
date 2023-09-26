<!DOCTYPE html>
<html>
<head>
    <title>INTERVALS</title>
</head>
<body>
<h1>INTERVALS</h1><hr>
<h4>Intervals</h4>

<form action="/api/v1/intervals/merge?kind=digits" method="post">
    <input type="submit" value="Объединить числовые интервалы и отправить в БД">
</form>

<form action="/api/v1/intervals/clear" method="post">
    <input type="submit" value="Clear">
</form>

<form action="/api/v1/intervals/min?kind=letters" method="post">
    <input type="submit" value="MinString">
</form>
<form action="/api/v1/intervals/min?kind=digits" method="post">
    <input type="submit" value="MinDigits">
</form>



<form action="/api/v1/intervals/merge?kind=letters" method="post">
    <input type="submit" value="Объединить буквенные интервалы и отправить в БД">
</form>

<h3>Создать новый интервал</h3>
<form action="/api/v1/intervals/add" method="post">
    START: <input type="text" name="start"/><br><br>
    END: <input type="text" name="end"/><br><br>
    <input type="submit" value="Добавить интервал"/>
</form>
<p><b>${intervals}</b></p>
<hr>
</body>
</html>