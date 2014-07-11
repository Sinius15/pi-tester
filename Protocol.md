General Info
=========

* ``<int>`` is the number of the target switch and must always be  >=1 and <=8. If not than an error will return: ``error_invalid_argument``.
* Errors alwas start wit ``error_``.

Server requests
======

| Client Request to Server| Server Action|Server Response|
| ------------- |-------------| -----|
| ``on <int>``| Turn on 1 of the switches.  | If succes ``succes``. If faild an error will be returned.  |
| ``off <int>``| Turn off 1 of the switches. |If succes ``succes``. If faild an error will be returned. |
| ``alloff``| Turns on all the switches at once |  If succes ``succes``. If faild an error will be returned. |
| ``allon`` | Turns off all the switches at once |  If succes ``succes``. If faild an error will be returned. |
| ``toggle <int>`` | Toggels 1 of the switches. | If succes ``succes_state_on`` or ``succes_state_off`` depending on the new state.  If faild an error will be returned. |
| ``state <int>``| Get the current state of one of the switches. | Returns the state with ``state_on`` and ``state_off`` depending on the state. If faild an error will be returned. |


Respons list
=========
* error_invalid_argument
* succes
* succes_state_on
* succes_state_off
* state_on
* state_off
