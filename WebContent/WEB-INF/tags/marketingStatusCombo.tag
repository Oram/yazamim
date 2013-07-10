<%@attribute name='id' required='false'%>
<%@attribute name="name" required="false"%>
<%@attribute name='bind' required='false'%>
<select id='${id}' name="${name}" data-bind='${bind}'>
    <option value='NONE'>לא שווק</option>
    <option value='LOW'>סיכויים נמוכים</option>
    <option value='MEDIUM'>עדיין לא ברור</option>
    <option value='HIGH'>סיכויים גבוהים</option>
    <option disabled value='SUCCESS'>התחיל פעילות</option>
</select>