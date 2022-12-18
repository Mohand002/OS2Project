# Sleeping Barber Problem

In computer science, the sleeping barber problem is a classic inter-process communication and synchronization problem that illustrates the complexities that arise when there are multiple operating system processes.

imagine a hypothetical barbershop with k barbers. When there are no customers, all barbers sleep in their chairs. The following rules apply:

1. If there are no customers, the barber falls asleep in the chair
2. A customer must wake the barber if he is asleep
3. If a customer arrives while the barber is working, the customer leaves if all chairs are occupied and sits in an empty chair if it's available
4. When the barber finishes a haircut, he inspects the waiting room to see if there are any waiting customers and falls asleep if there are none
