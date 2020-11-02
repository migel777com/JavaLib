<%-- Index --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div class="controlPanel" style="height: 95%;background-image: url('img/index.jpg');background-size: cover;">
  <div class="controlPanelBlock">
    <center>
      <img src="img/books-stack-of-three.png" width="100" height="100" alt="books-stack-of-three.icon" style="background-color: rgba(250,235,215,0.7); border-radius: 10px;margin-bottom: 3%"><br>
      <div style="background-color: RGB(10,106,12); border-radius: 25px;border: 4px solid RGB(150,12,39);width: 40vw;padding: 1%">
        <p style="font-size: 35pt;">Welcome to our Library!</p><br>
        <div style="display: inline-block;left: 50%">
          <div id="login">
            <jsp:include page="login.jsp"/>
          </div>
          <!-- <div id="registration">
                        "<jsp:include page="registration.jsp"/>
               </div> -->
        </div>
      </div>
    </center>
  </div>
</div>

<jsp:include page="footer.jsp"/>
