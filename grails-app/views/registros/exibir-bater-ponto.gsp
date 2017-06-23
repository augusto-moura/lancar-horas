<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="custom"/>
	<title>Bater ponto</title>
</head>

<body>
<g:form class="form-inline text-center" controller="registros" action="bater-ponto">
	<div id="clock" class="lead"></div>
	<button class="btn btn-primary btn-lg"><i class="glyphicon glyphicon-time"></i> Bater ponto</button>
</g:form>

<script>
    window.document.addEventListener('DOMContentLoaded', () => {
        const clock = document.getElementById('clock');
        const refreshClock = () => {
            clock.innerHTML = new Date().toLocaleString();
        };

        refreshClock();
        window.setInterval(refreshClock, 1000);
    });
</script>
</body>

</html>