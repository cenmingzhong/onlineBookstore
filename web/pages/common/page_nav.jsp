<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/24 0024
  Time: 下午 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--分页的开始--%>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>


    <%--页码输出的开始--%>
    <c:choose>
        <%--情况1：如果总页码小于等于5，页码范围是1-总页码--%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${i!=requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>

                </c:if>
                <c:if test="${i==requestScope.page.pageNo}">
                    【${i}】
                </c:if>


            </c:forEach>

        </c:when>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:forEach begin="1" end="5" var="i">
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>

                        </c:if>
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>


                    </c:forEach>
                </c:when>
                <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
                    <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>

                        </c:if>
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>


                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>

                        </c:if>
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>


                    </c:forEach>

                </c:otherwise>

            </c:choose>


        </c:when>
    </c:choose>



    <%--页码输出的结束--%>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${ requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCout}条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            //跳到指定的页码
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();



                location.href="${pageScope.basePath}${requestScope.page.url}&pageNo="+pageNo;
            });

        });

    </script>
</div>
<%--分页的结束--%>
