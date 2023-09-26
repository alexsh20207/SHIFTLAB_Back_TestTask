<!DOCTYPE html>
<html>
<head>
    <title>INTERVALS</title>
</head>
<body>
<h1>INTERVAL</h1><hr>
<h4>Подробная информация об интервале</h4>
<b>START: </b>${interval.start}<br>
<b>END: </b>${interval.end}<br>
<hr>
<form action="/interval/delete/${interval.id}" method="post">
    <input type="submit" value="Удалить интервал"/>
</form>
</body>
</html>