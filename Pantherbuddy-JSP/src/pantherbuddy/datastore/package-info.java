/**
 * The package contains the implementation of the data tier of the 3-tier architecture followed for developing
 * the PantherBuddy system. It occupies the bottom most level and includes the data persistence mechanisms
 * (database servers, file shares, etc.) and the data access layer that encapsulates the persistence mechanisms
 * and exposes the data. The data access layer provides an interface to the application tier that exposes methods of
 * managing the stored data without exposing or creating dependencies on the data storage mechanisms. Avoiding
 * dependencies on the storage mechanisms allows for updates or changes without the application tier clients being
 * affected by or even aware of the change.
 *
 * @author ALLAN
 *
 */
package pantherbuddy.datastore;