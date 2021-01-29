# DefaultApi

All URIs are relative to *https://virtserver.swaggerhub.com/999JackFlash/sample/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**usersUserIdGet**](DefaultApi.md#usersUserIdGet) | **GET** /users/{userId} | Returns a user by ID.

<a name="usersUserIdGet"></a>
# **usersUserIdGet**
> InlineResponse200 usersUserIdGet(userId)

Returns a user by ID.

### Example
```kotlin
// Import classes:
//import io.swagger.client.infrastructure.*
//import io.swagger.client.models.*;

val apiInstance = DefaultApi()
val userId : kotlin.Int = 56 // kotlin.Int | The ID of the user to return
try {
    val result : InlineResponse200 = apiInstance.usersUserIdGet(userId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#usersUserIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#usersUserIdGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **kotlin.Int**| The ID of the user to return |

### Return type

[**InlineResponse200**](InlineResponse200.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

