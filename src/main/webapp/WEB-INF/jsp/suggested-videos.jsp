<jsp:include page="header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<main role="main" class="container">
  <section>
    <div class="container py-5">
      <c:forEach items="${videoList}" var="video">
        <div class="row justify-content-center mb-3">
          <div class="col-md-12 col-xl-10">
            <div class="card shadow-0 border rounded-3">
              <div class="card-body">
                <div class="row">
                  <div class="col-md-12 col-lg-3 col-xl-3 mb-4 mb-lg-0">
                    <div class="bg-image hover-zoom ripple rounded ripple-surface">
                      <a target='_blank' href="${video.videoUrl}">
                      <img src="${video.thumbnail}" class="w-100" />
                        <div class="hover-overlay">
                          <div class="mask" style="background-color: rgba(253, 253, 253, 0.15);"></div>
                        </div>
                      </a>
                    </div>
                  </div>
                  <div class="col-md-6 col-lg-6 col-xl-6">
                    <h5>${video.name}</h5>
                    <div class="d-flex flex-row">
                      <div class="text-danger mb-1 me-2">
                        <c:forEach begin="1" end="${video.rating}" varStatus="loop">
                          <i class="fa fa-star"></i>
                        </c:forEach>
                      </div>
                      <span>${video.ratingCount}</span>
                    </div>
                    <div class="mt-1 mb-0 text-muted small">
                    <c:forEach items="${video.topics}" var="topic">
                        <span>${topic}</span>
                        <span class="text-primary">&bull;</span>
                    </c:forEach>
                    </div>
                    <p class="text-truncate mb-4 mb-md-0">${video.detail}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

      </c:forEach>

    </div>
  </section>
</main>

