package com.samclub.astronomypicture.data.excepions

import java.io.IOException

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)
class GsonException(message: String) : Exception(message)