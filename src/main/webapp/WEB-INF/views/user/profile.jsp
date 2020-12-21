<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="/resources/css/style.css"/>
</head>
<body>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                Witaj <sec:authentication property="name"/>
                <ul class="dropdown">
                    <li><a href="/user/profile/">Profil</a></li>
                    <li><a href="#">Moje zbiórki</a></li>
                    <li><a href="/logout">Wyloguj</a></li>
                </ul>
            </li>
        </ul>

        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="index.html#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="index.html#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="index.html#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="index.html#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>

<section class="login-page">
    <h2>Edytuj profil</h2>
    <form:form method="post" modelAttribute="user" >
        <form:hidden path="id"/>
        <div class="form-group">
            <form:input path="username"/>
        </div>
        <div class="form-group">
            <form:password path="password"/>
        </div>
        <div class="form-group">
            <form:input path="firstName"/>
        </div>
        <div class="form-group">
            <form:input path="lastName"/>
        </div>

        <div class="form-group form-group--buttons">
           <input type="submit" value="Edytuj">
        </div>
    </form:form>
</section>

<jsp:include page="../footer.jsp"/>
</body>
</html>

