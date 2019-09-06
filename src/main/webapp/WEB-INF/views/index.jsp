<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%-- pas besoin de définir une action, celle par défaut convient --%>
<form:form method="post" modelAttribute="infos">
    <table>
        <tr>
            <td>Nom</td>
            <td><form:input path="email" /></td>
        </tr>
        <tr>
            <td>Prenom</td>
            <td><form:input path="motDePasse" /></td>
        </tr>
         <tr>
            <td></td>
            <td> <input type="submit" value="Se connecter"></td>
        </tr>
       
    </table>
</form:form>