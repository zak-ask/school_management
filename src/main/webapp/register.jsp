<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css">
    <title>Document</title>
</head>
<body>
    <section class="h-100 bg-dark">
        <div class="container py-5 h-100">
          <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col">
              <div class="card card-registration my-4">
                <div class="row g-0">
                  <div class="col-xl-6 d-none d-xl-block">
                    <img src="image/signe.png"
                      alt="Sample photo" class="img-fluid"
                      style="border-top-left-radius: .25rem; border-bottom-left-radius: .25rem;" />
                  </div>
                  <div class="col-xl-6 ">
                    <div class="card-body p-md-5 text-black m-2">
                      <h3 class="mb-5 text-uppercase">formulaire de registration</h3>
      
                      <div class="row">
                        <div class="col-md-6 mb-4">
                          <div class="form-outline">
                            <input type="text" id="form3Example1m" class="form-control form-control-lg" />
                            <label class="form-label" for="form3Example1m">First name</label>
                          </div>
                        </div>
                        <div class="col-md-6 mb-4">
                          <div class="form-outline">
                            <input type="text" id="form3Example1n" class="form-control form-control-lg" />
                            <label class="form-label" for="form3Example1n">Last name</label>
                          </div>
                        </div>
                      </div>
      
                      <div class="row">
                        <div class="col-md-6 mb-4">
                          <div class="form-outline">
                            <input type="text" name="mothername" id="form3Example1m1" class="form-control form-control-lg" />
                            <label class="form-label" for="form3Example1m1">Mother's name</label>
                          </div>
                        </div>
                        <div class="col-md-6 mb-4">
                          <div class="form-outline">
                            <input type="text"  name="fathername" id="form3Example1n1" class="form-control form-control-lg" />
                            <label class="form-label" for="form3Example1n1">Father's name</label>
                          </div>
                        </div>
                      </div>
      
                      <div class="form-outline mb-4">
                        <label class="form-label" for="adress">Address</label>
                        <input type="text" id="adress" name="adress" class="form-control form-control-lg" />
                        <label class="form-label" for="cin">Cin</label>
                        <input type="text" id="cin" name="cin" placeholder="Serie CIN " class="form-control w-75 form-control-lg" />
                      </div>
      
                      <div class="d-md-flex justify-content-start align-items-center mb-4 py-2">
      
                        <h6 class="mb-0 me-4">Gender: </h6>
      
                        <div class="form-check form-check-inline mb-0 me-4">
                          <input class="form-check-input" type="radio" name="inlineRadioOptions" id="femaleGender"
                            value="option1" />
                          <label class="form-check-label" for="femaleGender">Female</label>
                        </div>
      
                        <div class="form-check form-check-inline mb-0 me-4">
                          <input class="form-check-input" type="radio" name="inlineRadioOptions" id="maleGender"
                            value="option2" />
                          <label class="form-check-label" for="maleGender">Male</label>
                        </div>
      
                      </div>
      
                       <div class="form-outline m-4">
                        <input type="text" id="form3Example9" class="form-control form-control-lg" />
                        <label class="form-label" for="form3Example9">DOB</label>
                      </div>
      
                      <div class="form-outline mb-4">
                        <input type="text" id="form3Example90" class="form-control form-control-lg" />
                        <label class="form-label" for="form3Example90">Pincode</label>
                      </div>
      
                      <div class="form-outline mb-4">
                        <input type="text" id="form3Example99" class="form-control form-control-lg" />
                        <label class="form-label" for="form3Example99">Course</label>
                      </div>
      
                      <div class="form-outline mb-4">
                        <input type="text" id="form3Example97" class="form-control form-control-lg" />
                        <label class="form-label" for="form3Example97">Email ID</label>
                      </div>
      
                      <div class="d-flex justify-content-end pt-3 m-2">
                        <a type="button" href="${pageContext.request.contextPath}/login" class="btn btn-danger btn-lg">Already registred. login.</a>
                        <button type="button" class="btn btn-primary btn-lg ms-2">Submit form</button>
                      </div>
          
                      </div>
      
                     
      
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
</body>
</html>