<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Connexion - Clinique</title>

    <style>
        body {
            margin: 0;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: Arial, sans-serif;
            background: #eef6f8;
        }

        .login-card {
            width: 350px;
            padding: 32px;
            border-radius: 12px;
            background: white;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
        }

        h1 {
            margin-top: 0;
            text-align: center;
            color: #197c8c;
        }

        label {
            display: block;
            margin: 15px 0 6px;
            font-weight: bold;
        }

        input {
            box-sizing: border-box;
            width: 100%;
            padding: 11px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        button {
            width: 100%;
            margin-top: 22px;
            padding: 12px;
            border: none;
            border-radius: 6px;
            color: white;
            background: #197c8c;
            cursor: pointer;
        }

        button:hover {
            background: #126574;
        }

        .error {
            padding: 10px;
            color: #a42121;
            background: #ffe5e5;
            border-radius: 6px;
        }
    </style>
</head>

<body>
    <div class="login-card">
        <h1>Clinique</h1>

        <% if (request.getAttribute("error") !=null) { %>
            <div class="error">
                <%= request.getAttribute("error") %>
            </div>
            <% } %>

                <form method="post" action="${pageContext.request.contextPath}/login">
                    <input type="hidden" name="csrfToken" value="${csrfToken}">
                    <label for="login">Nom d'utilisateur</label>
                    <input id="login" name="login" type="text" placeholder="nom@exemple.com" required>

                    <label for="password">Mot de passe</label>
                    <input id="password" name="password" type="password" placeholder="Votre mot de passe" required>

                    <button type="submit">Se connecter</button>
                </form>
    </div>
</body>

</html>