<html>
<body>
<form method="post" action="index" autocomplete="off">
    <div>
        <label for="username" class="login">Username</label>
        <input id="username" name="username" autocomplete="off" type="text"/>
    </div>
    <input value="Add user" type="submit" id="submit"/>
</form>
<font color="red" id="status">Status: ${status}</font>
<#list users as user>
<p id="users">${user}</p>
</#list>
</body>
</html>